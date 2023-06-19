package com.example.serverkfu.service;

import com.example.serverkfu.entities.ErrorAnalytics;
import com.example.serverkfu.entities.LinkErrorAnalytics;
import com.example.serverkfu.model.ItemConfiguration;
import com.example.serverkfu.repository.ErrorAnalyticsRepository;
import com.example.serverkfu.repository.LinkAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnalyticsService {
    @Autowired
    ErrorAnalyticsRepository errorRepository;

    @Autowired
    LinkAnalyticsRepository linksRepository;

    public List<LinkErrorAnalytics> getLinkErrors(String version) {
        return linksRepository.getErrorsByVersion(version);
    }

    public void addAnalytics(LinkErrorAnalytics error) {
        linksRepository.save(error);
    }

    public void addErrorAnalytics(ErrorAnalytics error) {
        errorRepository.save(error);
    }
}
