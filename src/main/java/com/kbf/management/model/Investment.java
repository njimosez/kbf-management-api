package com.kbf.management.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "investment")
public class Investment {
    @Id @GeneratedValue
    private Long investmentId;
    
    /** A human‑readable name or description of the investment. */
    @Column(nullable = false)
    private String name;

    /** The total cost of the asset at acquisition. */
    @Column(nullable = false)
    private BigDecimal acquisitionCost;

    /** Date when the asset was acquired. */
    @Column(nullable = false)
    private LocalDate acquisitionDate;

    /** Expected useful life in years (for straight‑line depreciation). */
    @Column(nullable = false)
    private int usefulLifeYears;

    /** Estimated salvage (residual) value at end of life. */
    private BigDecimal salvageValue;

    /**
     * Depreciation method.
     * e.g. STRAIGHT_LINE, DOUBLE_DECLINING
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DepreciationMethod depreciationMethod;

    /** Current accumulated depreciation to date. */
    @Column(nullable = false)
    private BigDecimal accumulatedDepreciation = BigDecimal.ZERO;

    /** Net book value = acquisitionCost – accumulatedDepreciation (calc’d at runtime). */
    @Transient
    public BigDecimal getNetBookValue() {
        return acquisitionCost.subtract(accumulatedDepreciation);
    }

    /**
     * Link any financial transaction(s) that funded this purchase.
     * Transaction.relatedEntityType = CAPITAL_INVESTMENT
     */
    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public enum DepreciationMethod {
        STRAIGHT_LINE,
        DOUBLE_DECLINING_BALANCE
    }
    
    
}
	 
	

