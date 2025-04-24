package com.kbf.management.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "feed_composition")
public class FeedComposition {
    @Id @GeneratedValue
    private Long feedCompId;
    private String name;
    @OneToMany
    private List<Ingredient> ingredients;
    private double proteinPercentage;
    private double fatPercentage;
    private double fiberPercentage;
    private double moisturePercentage;
    private double ashPercentage;
    private double premixPercentage;
    private String notes;
   
    
}
