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
@Table(name = "veterinary_records")
public class Veterinary {
    @Id @GeneratedValue
    private Long vetId;    
    private String animalType;           // e.g., Fish, Poultry, Cattle
    private String speciesOrBreed;       // e.g., Tilapia, Broiler, Holstein
    private LocalDate visitDate;
    private String veterinarianName;
    private String diagnosis;
    private String treatmentGiven;
    private String medication;           // e.g., Oxytetracycline
    private double dosage;               // optional
    private String dosageUnit;           // e.g., mg/kg
    private String notes;

	
	 @ManyToOne(fetch = FetchType.LAZY)
		//@JsonIgnore
		@JoinColumn(name = "fishStockId")
		private FishStock fishStock;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
		//@JsonIgnore
		@JoinColumn(name = "animalStockId")
		private AnimalStock animalStock;
	
}
