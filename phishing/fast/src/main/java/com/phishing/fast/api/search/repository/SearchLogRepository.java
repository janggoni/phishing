package com.phishing.fast.api.search.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.phishing.fast.api.search.document.SearchLog;

public interface SearchLogRepository extends MongoRepository<SearchLog, String> {

    // MongoDB 커스텀 쿼리: 키워드별로 검색 횟수를 카운트
    @Query(value = "{ 'keyword': ?0 }", count = true)
    long countByKeyword(String keyword);
}
