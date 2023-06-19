package com.example.serverkfu.repository;

import com.example.serverkfu.entities.Configuration;
import com.example.serverkfu.model.ItemConfiguration;
import com.example.serverkfu.service.ConfigService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConfigRepository extends JpaRepository<Configuration, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Configuration WHERE version = :version")
    List<Configuration> getConfigsByVersion(String version);


    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM Configuration WHERE version = :version")
    void removeByVersion(String version);
}
