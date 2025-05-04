package com.kbf.management.model;


import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "probiotics")
public class Probiotic {
    @Id @GeneratedValue
    private Long probioticId;  
    
    /** Date of probiotic application */
    @Column(nullable = false)
    private LocalDate applicationDate;

    /** Probiotic product name , example = "Bacillus subtilis" */
    @Column(nullable = false)
    private String probioticName;

    /** Purpose of application */
    private String purpose;

    /** Method of application (e.g., in feed, in water) */
    private String method;

    /** Quantity applied */
    @Column(nullable = false)
    private double quantity;

    /** Unit of quantity (e.g., g, kg) */
    @Column(nullable = false)
    private String unit;

    /** Concentration of probiotic (e.g., CFU/ml) */
    private String concentration;

    /** Manufacturing date of the probiotic product */
    private LocalDate manufactureDate;

    /** Expiry date of the probiotic product */
    private LocalDate expiryDate;

    /** Usage instructions */
    @Column(columnDefinition = "TEXT")
    private String usageInstructions;

    /** Remarks or notes */
    @Column(columnDefinition = "TEXT")
    private String remarks;

    /** Associated water treatments using this probiotic */
    @OneToMany(mappedBy = "probioticApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WaterTreatment> waterTreatments;

    /** Financial transactions related to this probiotic purchase */
    @OneToMany(mappedBy = "probioticApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
    
    /** Pond to which this probiotic was applied (optional) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pondId")
    private Pond pond;

    /** Fish stock batch to which this probiotic was applied (optional) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fishStockId")
    private FishStock fishStock;

    /** Supplier from whom the probiotic was sourced */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplierId", nullable = false)
    private Supplier supplier;
}