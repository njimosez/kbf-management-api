package com.kbf.management.model;

import java.io.Serializable;
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
@Table(name = "provender")
public class Provender implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long provenderId;
	
	/** e.g. “Starter Feed”, “Grower Mix” */
    @Column(nullable = false)
    private String feedType;

    /** Current stock level (kg) */
    @Column(nullable = false)
    private double quantity;

    /** When this batch was last restocked */
    private LocalDate lastRestocked;

    /** Price per kilogram */
    private double pricePerKg;

    /** Expiry date of this feed batch */
    private LocalDate expiryDate;

    /** Any special feeding recommendations */
    @Column(columnDefinition = "TEXT")
    private String feedingNotes;

    /** Who manufactured this feed */
    private String manufacturer;
	
	@OneToMany(mappedBy = "provender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedComposition> feedCompositions;

    @OneToMany(mappedBy = "provender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
    
    @OneToMany(mappedBy = "provender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedUsage> feedUsages;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplierId", nullable = false)
    private Supplier supplier;



}
