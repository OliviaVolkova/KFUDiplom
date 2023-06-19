package com.example.serverkfu.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemConfiguration {
    public String title;
    public Boolean isChecked;
    public String link;
}
