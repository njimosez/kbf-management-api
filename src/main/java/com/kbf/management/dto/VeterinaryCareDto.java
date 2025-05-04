package com.kbf.management.dto;


import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for VeterinaryCare records")
public class VeterinaryCareDto {

    @Schema(description = "Veterinary care record ID", example = "1")
    private Long id;

    @NotNull
    @Positive
    @Schema(description = "ID of the fish stock treated", example = "7")
    private Long fishStockId;

    @NotNull
    @Schema(description = "Date of veterinary intervention", example = "2025-05-11")
    private LocalDate careDate;

    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Dosage administered", example = "2.5")
    private double dosage;

    @NotBlank
    @Schema(description = "Unit for dosage", example = "mg/kg")
    private String dosageUnit;

    @Schema(description = "Type of animal", example = "Fish")
    private String animalType;

    @Schema(description = "Species or breed of the animal", example = "Tilapia")
    private String speciesOrBreed;

    @Schema(description = "Medication or procedure used", example = "Antibiotic bath")
    private String medication;

    @Schema(description = "Diagnosis or reason for care", example = "Fin rot")
    private String diagnosis;

    @Schema(description = "Treatment administered", example = "Antibiotic bath")
    private String treatment;

    @Schema(description = "Name of veterinarian or staff", example = "Dr. Smith")
    private String veterinarianName;

    @Schema(description = "Additional notes or observations", example = "Improvement noticed after 3 days")
    private String notes;

    @Schema(description = "Associated transaction ID if recorded", example = "12")
    private Long transactionId;
}



