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
@Table(name = "water_treatments")
public class WaterTreatment {
    @Id @GeneratedValue
    private Long treatmentId;    
    private LocalDate treatmentDate;
    private String pondName;
    private String treatmentType;      // e.g., pH adjustment, oxygenation, chemical disinfectant
    private String chemicalUsed;       // e.g., Lime, Potassium permanganate
    private double dosage;             // Amount applied
    private String dosageUnit;         // e.g., mg/L, g/m³
    private String appliedBy;          // Technician or staff name
    private String notes;

	
	 @ManyToOne(fetch = FetchType.LAZY)
		//@JsonIgnore
		@JoinColumn(name = "pondId")
		private Pond pond;
	 
	@OneToMany(mappedBy = "waterTreatment",fetch = FetchType.LAZY)
	private List<Probiotics> probiotics;
	
}
