package com.example.serverkfu.repository;

import com.example.serverkfu.entities.Configuration;
import com.example.serverkfu.entities.ErrorAnalytics;
import com.example.serverkfu.entities.LinkErrorAnalytics;
import com.example.serverkfu.model.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinkAnalyticsRepository extends JpaRepository<LinkErrorAnalytics, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM LinkAnalytics WHERE version = :version")
    List<LinkErrorAnalytics> getErrorsByVersion(String version);
}
