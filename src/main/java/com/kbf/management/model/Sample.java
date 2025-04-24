package com.kbf.management.model;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "samples")
public class Sample {
    @Id @GeneratedValue
    private Long sampleId;
    private String specy;
	private LocalDate sampleDate;
	private int sampleQty;
	private double totalSampleWeight;
	private double avgFishSize;
	private double targetSize;
   
	@ManyToOne
	  //@JsonIgnore
	  @JoinColumn(name = "fishStockId")
	  private FishStock fishStock;
	
	@ManyToOne
	  //@JsonIgnore
	  @JoinColumn(name = "animalStockId")
	  private AnimalStock animalStock;
	 
}
