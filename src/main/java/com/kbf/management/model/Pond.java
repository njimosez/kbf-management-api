package com.kbf.management.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
@Table(name = "ponds")
public class Pond {
    @Id @GeneratedValue
    private Long pondId;
    
    /** Name of the pond */
    @Column(nullable = false, unique = true)
    private String name;

    /** Physical size in square meters */
    @Column(nullable = false)
    private double size;

    /** Geographic or facility location description */
    private String location;

    /** Current operational status (e.g., Active, Drained) */
    private String status;

    /** Maximum fish capacity */
    private int fishCapacity;

    
    @JsonIgnore
    /** Fish stock batches residing in this pond */
    @OneToMany(mappedBy = "pond")
    private List<FishStock> fishStocks;

    /** Water analysis records for this pond */
    @OneToMany(mappedBy = "pond")
    private List<WaterAnalysis> waterAnalyses;

    /** Water treatment events for this pond */
    @OneToMany(mappedBy = "pond")
    private List<WaterTreatment> waterTreatments;
}
