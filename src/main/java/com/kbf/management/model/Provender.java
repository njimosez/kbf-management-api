package com.kbf.management.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Provender {
	
	@Id @GeneratedValue
    private Long id;
    private String name;
    private double quantityInKg;
    private LocalDate expiryDate;
    private String type; // e.g., Fish, Poultry
    

}
