package com.kbf.management.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "water_analyses")
public class WaterAnalysis {
	@Id
	@GeneratedValue
	private Long analysisId;
	
	/** Associated pond for this analysis */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pond_id", nullable = false)
    private Pond pond;

    /** pH level of the water */
    @Column(nullable = false)
    private double ph;

    /** Ammonia concentration (mg/L) */
    @Column(nullable = false)
    private double ammonia;

    /** Nitrite concentration (mg/L) */
    @Column(nullable = false)
    private double nitrite;

    /** Nitrate concentration (mg/L) */
    @Column(nullable = false)
    private double nitrate;

    /** Dissolved oxygen (mg/L) */
    @Column(nullable = false)
    private double oxygen;

    /** Water temperature (°C) */
    @Column(nullable = false)
    private double temperature;

    /** Water hardness (mg/L) */
    @Column(nullable = false)
    private double hardness;

    /** Water alkalinity (mg/L) */
    @Column(nullable = false)
    private double alkalinity;

    /** Additional notes or observations */
    @Column(columnDefinition = "TEXT")
    private String notes;

    /** Date when the analysis was conducted */
    @Column(nullable = false)
    private LocalDate analysisDate;
}


