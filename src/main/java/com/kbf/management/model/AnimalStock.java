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
@Table(name = "animalStock")
public class AnimalStock {
    @Id @GeneratedValue
    private Long animalStockId;    
    private String batch;
	private AnimalType type;
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
	
	
	 @OneToMany(mappedBy = "animalStock",fetch = FetchType.LAZY)
	 private List<Sample> sample;
	 
	@OneToMany(mappedBy = "animalStock",fetch = FetchType.LAZY)
	private List<Provender> provender;
	
	@OneToMany(mappedBy = "animalStock",fetch = FetchType.LAZY)
	private List<Veterinary> vetcare;
	
	
	
	
	
}
