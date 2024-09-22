package com.phishing.fast.api.search.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

import com.phishing.fast.api.search.document.PhishingRecordDocument;
import com.phishing.fast.api.search.document.SearchLog;
import com.phishing.fast.datasources.elasticsearch.repository.PhishingRecordElasticsearchRepository;
import com.phishing.fast.api.search.repository.PhishingRecordRepository;
import com.phishing.fast.api.search.repository.SearchLogRepository;
import com.phishing.fast.api.search.vo.PhishingRecord;

import java.util.List;

//Kafka Consumer Service
@Service
public class SearchLogConsumerService {

    @Autowired
    private PhishingRecordRepository phishingRecordRepository;

    @Autowired
    private PhishingRecordElasticsearchRepository phishingRecordElasticsearchRepository;

    @Autowired
    private SearchLogRepository searchLogRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "search-topic", groupId = "search-group")
    public void consume(String message) {
        // 메시지 처리 예시 (JSON parsing 등)
        String[] data = message.split(","); // 예: "keyword, userId"
        String keyword = data[0];
        String userId = data[1];

        // MariaDB에서 검색
        List<PhishingRecord> results = phishingRecordRepository.findByDescriptionContaining(keyword);

        // 검색 결과 Elasticsearch에 저장
        for (PhishingRecord record : results) {
            PhishingRecordDocument document = new PhishingRecordDocument();
            document.setDescription(record.getDescription());
            document.setDate(record.getDate());
            phishingRecordElasticsearchRepository.save(document);
        }

        // 검색 로그를 MongoDB에 저장
        SearchLog log = new SearchLog();
        log.setKeyword(keyword);
        log.setUserId(userId);
        log.setTimestamp(LocalDateTime.now().toString());
        searchLogRepository.save(log);

        // 검색 결과 Kafka를 통해 Elasticsearch로 전달
        kafkaTemplate.send("elasticsearch-topic", "Search completed for keyword: " + keyword);
    }
}
