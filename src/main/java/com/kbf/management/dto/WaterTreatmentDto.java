package com.kbf.management.dto;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for WaterTreatment records")
public class WaterTreatmentDto {

    @Schema(description = "Treatment record ID", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Treatment type (e.g., Chlorine, Lime, Probiotic)", example = "Chlorine")
    private String treatmentType;

    @Schema(description = "Chemical or substance used (if applicable)", example = "Sodium Hypochlorite")
    private String chemicalUsed;

    @Positive
    @Schema(description = "ID of the ProbioticApplication (if treatmentType=Probiotic)", example = "4")
    private Long probioticApplicationId;

    @NotNull
    @Schema(description = "Date of treatment application", example = "2025-05-06")
    private LocalDate treatmentDate;

    @Schema(description = "Observed effectiveness notes", example = "Algae levels reduced significantly")
    private String effectiveness;

    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Amount of treatment applied", example = "2.5")
    private double dosage;

    @NotBlank
    @Schema(description = "Unit for dosage (e.g., mg/L, g/m³)", example = "mg/L")
    private String dosageUnit;

    @NotBlank
    @Schema(description = "Technician or staff who applied the treatment", example = "John Doe")
    private String appliedBy;

    @NotNull
    @Positive
    @Schema(description = "ID of the pond treated", example = "1")
    private Long pondId;
}





