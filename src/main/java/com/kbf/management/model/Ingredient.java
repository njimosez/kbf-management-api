package com.kbf.management.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double costPerUnit;
    private double quantityInKg;
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Double> nutrientComposition;
    private LocalDate expiryDate;
    private ProvenderType type;
    
    
    
}
