package com.kbf.management.model;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "water_analyses")
public class WaterAnalysis {
    @Id @GeneratedValue
    private Long analysisId;    
    private LocalDate sampleDate;
    private String pondName;
    private double temperature;    // in Celsius
    private double pH;
    private double dissolvedOxygen; // in mg/L
    private double ammonia;         // in mg/L
    private double nitrite;         // in mg/L
    private double nitrate;         // in mg/L
    private double hardness;        // in mg/L
    private double alkalinity;      // in mg/L
    private String notes;

	
	 @ManyToOne(fetch = FetchType.LAZY)
		//@JsonIgnore
		@JoinColumn(name = "pondId")
		private Pond pond;
	
	
	
}
