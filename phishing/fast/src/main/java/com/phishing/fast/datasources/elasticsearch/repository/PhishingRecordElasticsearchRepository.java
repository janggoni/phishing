package com.phishing.fast.datasources.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.phishing.fast.api.search.document.PhishingRecordDocument;
public interface PhishingRecordElasticsearchRepository extends ElasticsearchRepository<PhishingRecordDocument, String> {

    // 커스텀 쿼리 메서드: Elasticsearch에서 description 필드를 검색
    @Query("{\"match\": {\"description\": {\"query\": \"?0\", \"operator\": \"and\"}}}")
    List<PhishingRecordDocument> searchByDescription(String keyword);
}
