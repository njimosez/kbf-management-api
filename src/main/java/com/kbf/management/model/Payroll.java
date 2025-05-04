package com.kbf.management.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
@Table(name = "payroll")
public class Payroll {
    @Id @GeneratedValue
    private Long payId;   
    
    /** Base salary amount */
    @Column(nullable = false)
    private double baseSalary;

    /** Bonus amount */
    @Column(nullable = false)
    private double bonus;

    /** Deduction amount */
    @Column(nullable = false)
    private double deduction;

    /** Net pay after calculations */
    @Column(nullable = false)
    private double netPay;

    /** Date of payment */
    @Column(nullable = false)
    private LocalDate paymentDate;
    
    /** Method of payment (e.g., Bank transfer, Cash) */
    @Column(nullable = false)
    private String paymentMethod;
    
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "personnelId", nullable = false)
    private Personnel personnel;
	
	/**
     * All transactions (e.g., payment entries) associated with this payroll.
     */
    @OneToMany(mappedBy = "payroll",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Transaction> transactions;
}
