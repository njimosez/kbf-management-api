package com.kbf.management.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "ponds")
public class Pond {
    @Id @GeneratedValue
    private Long pondId;
    private String pondName;
    private String species;
    private double capacity;
    private double waterPH;
    private double temperature;
    private boolean isActive;
    private int fishInStock;
    
    @JsonIgnore
	  @OneToMany(mappedBy = "pond")
	  private List<WaterAnalysis> waterAnalysis;
	  
	 @JsonIgnore
		  @OneToMany(mappedBy = "pond")
		  private List<WaterTreatment> waterTreatment;
}
