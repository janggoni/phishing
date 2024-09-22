package com.phishing.fast.api.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phishing.fast.api.search.document.SearchLog;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // 검색 요청을 받는 엔드포인트
    @RequestMapping(value = "/keyword")
    public String search(@RequestBody SearchLog request) {
        // 검색 요청을 Kafka로 전송
        String message = request.getKeyword() + "," + request.getUserId();
        kafkaTemplate.send("search-topic", message);
        return "검색 요청이 접수되었습니다: " + request.getKeyword();
    }

}
