package com.kbf.management.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nutrients")
public class NutrientRequirement {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double minLevel;
    private double maxLevel;
    
    
}
