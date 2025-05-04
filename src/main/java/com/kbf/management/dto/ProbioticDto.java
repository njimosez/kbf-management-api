package com.kbf.management.dto;


import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for ProbioticApplication records")
public class ProbioticDto {

    @Schema(description = "Probiotic application ID", example = "1")
    private Long id;

    @Schema(description = "ID of the pond treated (optional)", example = "1")
    private Long pondId;

    @Schema(description = "ID of the fish stock batch treated (optional)", example = "5")
    private Long fishStockId;

    @NotNull
    @Positive
    @Schema(description = "ID of the supplier", example = "2")
    private Long supplierId;

    @NotNull
    @Schema(description = "Date of application", example = "2025-05-10")
    private LocalDate applicationDate;

    @NotBlank
    @Schema(description = "Probiotic product name", example = "BioPro-X")
    private String probioticName;

    @Schema(description = "Purpose of application", example = "Improve gut health")
    private String purpose;

    @Schema(description = "Method of application", example = "In-water dosing")
    private String method;

    @Positive
    @Schema(description = "Quantity applied", example = "100.0")
    private double quantity;

    @NotBlank
    @Schema(description = "Unit of quantity", example = "g")
    private String unit;

    @Schema(description = "Concentration (e.g., CFU/ml)", example = "1e9 CFU/ml")
    private String concentration;

    @Schema(description = "Manufacture date of the product", example = "2025-01-01")
    private LocalDate manufactureDate;

    @Schema(description = "Expiry date of the product", example = "2026-01-01")
    private LocalDate expiryDate;

    @Schema(description = "Usage instructions", example = "Apply at dawn, remix before use")
    private String usageInstructions;

    @Schema(description = "Additional remarks", example = "Store in cool, dry place")
    private String remarks;

    @Schema(description = "IDs of related water treatments")
    private List<Long> waterTreatmentIds;

    @Schema(description = "IDs of related transactions")
    private List<Long> transactionIds;
}




