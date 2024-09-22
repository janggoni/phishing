package com.phishing.fast.api.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phishing.fast.api.search.vo.PhishingRecord;

public interface PhishingRecordRepository extends JpaRepository<PhishingRecord, Long> {
    List<PhishingRecord> findByDescriptionContaining(String keyword);
}
