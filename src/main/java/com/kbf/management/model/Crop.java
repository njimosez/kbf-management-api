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
@Table(name = "crops")
public class Crop {
    @Id @GeneratedValue
    private Long cropId;
    private String name;
    private String fieldName;
    private LocalDate plantingDate;
    private LocalDate expectedHarvestDate;
    private double areaSize;
}
