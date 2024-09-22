package com.phishing.fast.api.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phishing.fast.api.search.document.PhishingRecordDocument;
import com.phishing.fast.api.search.repository.SearchLogRepository;
import com.phishing.fast.datasources.elasticsearch.repository.PhishingRecordElasticsearchRepository;

@Service
public class PhishingSearchService {

    // private final SearchLogRepository searchLogRepository; // jpa 리포지토리
    // private final PhishingRecordElasticsearchRepository
    // phishingRecordElasticsearchRepository; // Elasticsearch 리포지토리

    // public PhishingSearchService(SearchLogRepository searchLogRepository,
    // PhishingRecordElasticsearchRepository phishingRecordElasticsearchRepository)
    // {
    // this.searchLogRepository = searchLogRepository;
    // this.phishingRecordElasticsearchRepository =
    // phishingRecordElasticsearchRepository;
    // }

    @Autowired
    private PhishingRecordElasticsearchRepository phishingRecordElasticsearchRepository;

    @Autowired
    private SearchLogRepository searchLogRepository;

    // 내용 검색
    public List<PhishingRecordDocument> searchByDescription(String keyword) {
        // Elasticsearch에서 description 필드로 검색
        return phishingRecordElasticsearchRepository.searchByDescription(keyword);
    }

    // 검색 통계 조회
    public long getSearchCount(String keyword) {
        // MongoDB에서 특정 키워드 검색 횟수 조회
        return searchLogRepository.countByKeyword(keyword);
    }
}
