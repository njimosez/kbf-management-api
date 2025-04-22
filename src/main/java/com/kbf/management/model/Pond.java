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
@Table(name = "pond")
public class Pond {
	
	@Id @GeneratedValue
    private Long id;
    private String name;
    private String species;
    private Double capacity;
    private double waterPH;
    private double temperature;
    private boolean isActive;
    private int fishInStock;
    

}
