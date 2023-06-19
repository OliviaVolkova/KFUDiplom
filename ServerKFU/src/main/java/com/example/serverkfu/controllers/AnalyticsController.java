package com.example.serverkfu.controllers;

import com.example.serverkfu.dto.ErrorDto;
import com.example.serverkfu.dto.LinkErrorDto;
import com.example.serverkfu.entities.ErrorAnalytics;
import com.example.serverkfu.entities.LinkErrorAnalytics;
import com.example.serverkfu.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class AnalyticsController {

    @Autowired
    AnalyticsService service;

    @GetMapping("/analytics")
    public String getAnalytics(ModelMap model, @RequestParam("version") Optional<String> version) {
        String resVersion = version.orElse("1.1");
        model.put("linkErrors", service.getLinkErrors(resVersion));
        model.put("version", resVersion);
        return "linkErrors";
    }

    @PostMapping("/linkError")
    public String postConfig(@RequestBody LinkErrorDto error) {
        service.addAnalytics(LinkErrorAnalytics.builder()
                .link(error.getLink())
                .version(error.getVersion())
                .build());
        return "redirect:/analytics";
    }

    @PostMapping("/error")
    public String postConfig(@RequestBody ErrorDto error) {
        service.addErrorAnalytics(ErrorAnalytics.builder()
                .description(error.getDescription())
                .version(error.getVersion())
                .build());
        return "redirect:/analytics";
    }
}
