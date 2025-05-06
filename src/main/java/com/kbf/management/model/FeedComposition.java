package com.kbf.management.model;

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
@Table(name = "feed_composition")
public class FeedComposition {
    @Id @GeneratedValue
    private Long feedCompId;
    
    private String name;
    
    /** Proportion percentage in the feed mix */
    @Column(nullable = false)
    private double percentage;

    /** Protein content contribution (g per kg) */
    private double protein;

    /** Fat content contribution (g per kg) */
    private double fat;

    /** Fiber content contribution (g per kg) */
    private double fiber;

    /** Energy contribution (kcal per kg) */
    private double energy;

    /** Flag indicating if this composition includes premix */
    @Column(nullable = false)
    private boolean premix;

    /** Moisture content percentage */
    @Column(nullable = false)
    private double moistureContent;

    /** Ash percentage */
    @Column(nullable = false)
    private double ashPercentage;

    /**
     * The ingredient entity in this composition.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    /**
     * Association back to the Provender (feed batch) this composition belongs to.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provender_id", nullable = false)
    private Provender provender;
}
