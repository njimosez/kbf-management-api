package com.kbf.management.model;


import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "fishStock")
public class FishStock {
    @Id @GeneratedValue
    private Long fishStockId;    
    private String batch;
	private String fishSpecy;
	private LocalDate stockDate;
	@NotNull(message = "totalStock is mandatory")
	private int totalStock;
	private int nbrOfDays;
	@NotBlank(message = "Purpose is mandatory")
	private String purpose;
	private int reduction;
	private int mortality;
	private int stockRemaining;
	private boolean isSoldOut;
	private int fishPondId;	
	private String fishPondName;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	//@JsonIgnore
	@JoinColumn(name = "pondId")
	private Pond pond;
	
	// @JsonIgnore
	 @OneToMany(mappedBy = "fishStock",fetch = FetchType.EAGER)
	  private List<Sample> sample;
	 @JsonIgnore
	@OneToMany(mappedBy = "fishStock",fetch = FetchType.LAZY)
	private List<Provender> provender;
	 @JsonIgnore
	@OneToMany(mappedBy = "fishStock",fetch = FetchType.LAZY)
	private List<Veterinary> vetcare;
	 
	  @JsonIgnore
	  @OneToMany(mappedBy = "fishStock",fetch = FetchType.LAZY)
	  private List<FeedUsage> feed;
	 // Could use the dropdown from fish,crops and animal by calling them also or make a dto for 
	  
	  @ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnore
		@JoinColumn(name = "transactonId")
		private Transaction transaction;
	
}
