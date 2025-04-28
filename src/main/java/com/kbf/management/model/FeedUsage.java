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
    private String feedType;
    private LocalDate feedingDate;
	private double qtyFed; // in kg
	@Column(columnDefinition = "integer default 0")
	private double avgFishSize;
	@Column(columnDefinition = "integer default 0")
	private int pelletSize;
	@Column(columnDefinition = "integer default 2")
	private int timesFed;
	@Column(columnDefinition = "integer default 0")
	private int fishInPond;
	@Column(columnDefinition = "integer default 0")
	private int reduced;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "animalStockId")
	private AnimalStock animalStock;
	
	 
	
}
