package com.phishing.fast.datasources.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.phishing.fast.datasources.elasticsearch.repository")
public class ElasticsearchConfig {

    // Elasticsearch RestClient 설정
    @Bean
    public RestClient restClient() {
        return RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
    }

    // ElasticsearchClient 설정
    @Bean
    public ElasticsearchClient elasticsearchClient(RestClient restClient) {
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

    // ElasticsearchOperations 빈 설정
    @Bean(name = "elasticsearchTemplate")
    public ElasticsearchOperations elasticsearchOperations(ElasticsearchClient elasticsearchClient) {
        return new ElasticsearchTemplate(elasticsearchClient);
    }
}
