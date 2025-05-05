package com.kbf.management.model;

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
@Table(name = "fishStock")
public class FishStock {
	@Id
	@GeneratedValue
	private Long fishStockId;
	/** Initial number of fish in this batch */
    @Column(name = "initial_stock", nullable = false)
    private int initialStock;

    /** Current remaining stock after sales and mortality */
    @Column(name = "stock_remaining", nullable = false)
    private int stockRemaining;

    /** Number of fish sold to date */
    @Column(name = "quantity_sold", nullable = false)
    private int qtySold;

    /** Number of fish lost due to mortality */
    @Column(nullable = false)
    private int mortality;

    /** Number of fish removed or reduced by other means */
    private int reduction;

    /** Flag indicating whether this batch is sold out */
    @Column(name = "is_sold_out")
    private boolean isSoldOut;

    /** Growth stage (e.g., Fingerling, Grower, Adult) */
    private String stage;

    /** Date when this stock was added */
    @Column(name = "date_added")
    private LocalDate dateAdded;

    /** Batch identifier or code */
    private String batch;
    
    private int productionCost;
    
    private int unitPriceSold ;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pondId", nullable = false)
    private Pond pond;

    @OneToMany(mappedBy = "fishStock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedUsage> feedUsages;
    
    @OneToMany(mappedBy = "fishStock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VeterinaryCare> veterinaryCare;

    @OneToMany(mappedBy = "fishStock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sample> samples;

    @OneToMany(mappedBy = "fishStock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Probiotic> probiotics;

    @OneToMany(mappedBy = "fishStock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

	

	
	

}
