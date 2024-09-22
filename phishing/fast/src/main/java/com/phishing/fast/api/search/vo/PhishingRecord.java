package com.phishing.fast.api.search.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// mariadb
@Entity
@Table(name = "PHISHING_RECORD")
public class PhishingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phishingRecordId;

    private String description;
    private String date;

    // 기본 생성자
    public PhishingRecord() {
    }

    // 모든 필드를 포함한 생성자
    public PhishingRecord(Long phishingRecordId, String description, String date) {
        this.phishingRecordId = phishingRecordId;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters
    public Long getphishingRecordId() {
        return phishingRecordId;
    }

    public void setphishingRecordId(Long phishingRecordId) {
        this.phishingRecordId = phishingRecordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
