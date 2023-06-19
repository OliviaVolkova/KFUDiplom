package com.example.serverkfu.dto;

import com.example.serverkfu.model.ItemConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigForm {
    String version;
    List<ItemConfiguration> configs;
}
