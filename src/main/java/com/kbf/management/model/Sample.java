package com.kbf.management.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
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
    
    /** Species sampled */
    @Column(nullable = false)
    private String specy;

    /** Date when the sampling occurred */
    @Column(nullable = false)
    private LocalDate sampleDate;

    /** Number of fish sampled */
    @Column(nullable = false)
    private int sampleQty;

    /** Total weight of sampled fish (grams) */
    @Column(nullable = false)
    private double totalSampleWeight;

    /** Average size of sampled fish (grams) */
    @Column(nullable = false)
    private double avgFishSize;

    /** Target size for management (grams) */
    @Column(nullable = false)
    private double targetSize;
   
	@ManyToOne
	  //@JsonIgnore
	  @JoinColumn(name = "fishStockId")
	  private FishStock fishStock;
	
	
	 
}
