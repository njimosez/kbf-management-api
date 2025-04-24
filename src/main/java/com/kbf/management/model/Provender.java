package com.kbf.management.model;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "provender")
public class Provender implements Serializable{
    private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
    private Long provenderId;
    private String name;
    private double quantityInKg;
    private LocalDate expiryDate;
    private String feedingNotes;
    private String manufacturer;
    private ProvenderType type;
    
    @ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumn(name = "feedCompId")
    private FeedComposition feedComposition;
    
    @ManyToOne
	//@JsonIgnore
	  @JoinColumn(name = "fishStockId")
	  private FishStock fishStock;
    
    @ManyToOne
   	//@JsonIgnore
   	  @JoinColumn(name = "animalStockId")
   	  private AnimalStock animalStock;
    
   
    
}
