package com.phishing.fast.api.search.document;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// mongo 
@Document(collection = "search_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLog {
    @Id
    private String searchLogId;
    private String keyword;
    private String timestamp;
    private String userId;

}
