package com.kbf.management.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "probiotics")
public class Probiotics {
    @Id @GeneratedValue
    private Long probioticId;
    private String name;              // e.g., Bacillus subtilis, Lactobacillus
    private String type;              // e.g., bacterial, fungal, mixed
    private String supplier;
    private double concentration;     // e.g., CFU/ml
    private String unit;              // e.g., CFU/g, CFU/ml
    private LocalDate manufactureDate;
    @OneToMany
    private List<Ingredient> ingredients;
    @OneToMany
    private List<NutrientRequirement> nutrientRequirements;
    private LocalDate expiryDate;
    private String usageInstructions;
    
    @ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumn(name = "treatmentId")
	private WaterTreatment waterTreatment;
    
}
