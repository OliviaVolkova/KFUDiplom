package com.example.serverkfu.controllers;

import com.example.serverkfu.dto.ConfigForm;
import com.example.serverkfu.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ConfigController {

    @Autowired
    ConfigService service;

    @GetMapping("/config")
    public String getConfig(ModelMap model, @RequestParam("version") Optional<String> version) {
        String resVersion = version.orElse("1.1");
        model.put("configs", service.getConfigurations(resVersion));
        model.put("version", resVersion);
        return "mainPage";
    }

    @PostMapping("/config")
    public String postConfig(ConfigForm form) {
        System.out.println(form);
        service.changeConfig(form.getVersion(), form.getConfigs());
        return "redirect:/config";
    }
}
