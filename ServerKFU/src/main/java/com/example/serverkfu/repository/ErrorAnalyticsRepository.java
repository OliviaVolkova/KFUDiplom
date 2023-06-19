package com.example.serverkfu.repository;

import com.example.serverkfu.entities.Configuration;
import com.example.serverkfu.entities.ErrorAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ErrorAnalyticsRepository extends JpaRepository<ErrorAnalytics, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM ErrorAnalytics WHERE version = :version")
    List<Configuration> getErrorsByVersion(String version);
}
