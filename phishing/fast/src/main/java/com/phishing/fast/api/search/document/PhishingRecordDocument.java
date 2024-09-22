package com.phishing.fast.api.search.document;

// Elasticsearch Document class

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "phishing-records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhishingRecordDocument {
    @Id
    private String phishingRecordDocumentId;

    private String description;
    private String date;

}
