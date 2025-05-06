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
@Table(name = "water_treatments")
public class WaterTreatment {
	@Id
	@GeneratedValue
	private Long treatmentId;	
	
    /** Treatment type (e.g., Chlorine, Lime, Probiotic) */
    @Column(nullable = false)
    private String treatmentType;

    /** Chemical or substance used */
    private String chemicalUsed;    

    /** Date of treatment application */
    @Column(nullable = false)
    private LocalDate treatmentDate;

    /** Observed effectiveness notes */
    @Column(columnDefinition = "TEXT")
    private String effectiveness;

    /** Amount of treatment applied */
    @Column(nullable = false)
    private double dosage;

    /** Unit for dosage (e.g., mg/L, g/m³) */
    @Column(nullable = false)
    private String dosageUnit;

    /** Technician or staff who applied the treatment */
    private String appliedBy;
    
    /** Pond that received the treatment */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pondId", nullable = false)
    private Pond pond;
    
    /** Probiotic application associated with this treatment */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "probioticId")
    private Probiotic probiotic;

}
