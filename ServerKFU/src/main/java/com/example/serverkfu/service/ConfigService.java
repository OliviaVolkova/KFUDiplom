package com.example.serverkfu.service;

import com.example.serverkfu.entities.Configuration;
import com.example.serverkfu.model.ItemConfiguration;
import com.example.serverkfu.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {

    @Autowired
    ConfigRepository repository;

    public List<ItemConfiguration> getConfigurations(String version) {
        return repository.getConfigsByVersion(version).stream().map(entity ->
                ItemConfiguration
                        .builder()
                        .link(entity.getLink())
                        .isChecked(entity.getIsChecked())
                        .title(entity.getTitle())
                        .build()
        ).toList();
    }

    public void changeConfig(String version, List<ItemConfiguration> configs) {
        repository.removeByVersion(version);
        repository.saveAll(configs.stream().map(itemConfiguration ->
                Configuration
                        .builder()
                        .isChecked(itemConfiguration.isChecked)
                        .link(itemConfiguration.link)
                        .title(itemConfiguration.title)
                        .build()
        ).toList());
    }
}
