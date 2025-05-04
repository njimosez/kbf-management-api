package com.kbf.management.model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "feed_usage")
public class FeedUsage {
    @Id @GeneratedValue
    private Long feedingId;
    
    /** Type of feed used (e.g., Starter, Grower) */
    @Column(nullable = false)
    private String feedType;
    
    /** Date when feed was applied */
    @Column(nullable = false)
    private LocalDate usageDate;
    
    /** Quantity of feed used in grams or kg */
    @Column(nullable = false)
    private double quantityUsed;
    
	@Column(columnDefinition = "integer default 0")
	private double avgFishSize;
	@Column(columnDefinition = "integer default 0")
	private int pelletSize;
	@Column(columnDefinition = "integer default 2")
	private int timesFed;
	@Column(columnDefinition = "integer default 0")
	private int fishInPond;
	 /** Number of fish removed from the pond on this day */
	@Column(columnDefinition = "integer default 0")
	private int reduced;
	 /** Number of fish mortalities observed during feeding */
	@Column(columnDefinition = "integer default 0")
	private int mortality;
	
	@Lob
	@Column
	private String feedingNotes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "fishStockId")
	private FishStock fishStock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "provenderId")
	private Provender provender;
	
	
	
	 
	
}
