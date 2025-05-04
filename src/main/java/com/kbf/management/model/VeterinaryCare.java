package com.kbf.management.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "veterinary_records")
public class VeterinaryCare {
    @Id @GeneratedValue
    private Long vetId;    
   

    /** Date of veterinary intervention */
    @Column(nullable = false)
    private LocalDate careDate;

    /** Dosage administered (amount) */
    @Column(nullable = false)
    private double dosage;

    /** Unit for dosage (e.g., mg/kg) */
    @Column(nullable = false)
    private String dosageUnit;

    /** Type of animal */
    private String animalType;

    /** Species or breed of animal */
    private String speciesOrBreed;

    /** Medication or procedure used */
    private String medication;

    /** Diagnosis or reason for care */
    private String diagnosis;

    /** Treatment given (medication, procedure) */
    private String treatment;

    /** Name of veterinarian or responsible staff */
    private String veterinarianName;

    /** Additional notes or observations */
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    
	
    /**
     * The fish stock batch this care record applies to.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fishStockId", nullable = false)
    private FishStock fishStock;
	 
    /** Associated transaction record if any */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction; 
	
}
