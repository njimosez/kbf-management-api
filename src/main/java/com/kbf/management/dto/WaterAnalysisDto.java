package com.kbf.management.dto;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for WaterAnalysis records")
public class WaterAnalysisDto {
	
	@Schema(description = "Analysis record ID", example = "1")
    private Long id;

    @NotNull
    @PositiveOrZero
    @Schema(description = "pH level of the water", example = "7.5")
    private double ph;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Ammonia concentration in mg/L", example = "0.2")
    private double ammonia;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Nitrite concentration in mg/L", example = "0.02")
    private double nitrite;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Nitrate concentration in mg/L", example = "1.5")
    private double nitrate;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Dissolved oxygen in mg/L", example = "6.8")
    private double oxygen;

    @NotNull
    @Schema(description = "Water temperature in °C", example = "24.3")
    private double temperature;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Water hardness in mg/L", example = "120.0")
    private double hardness;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Water alkalinity in mg/L", example = "80.0")
    private double alkalinity;

    @Schema(description = "Additional notes or observations", example = "Clear water, no smell")
    private String notes;

    @NotNull
    @Schema(description = "Date of analysis", example = "2025-05-05")
    private LocalDate analysisDate;

    @NotNull
    @Positive
    @Schema(description = "ID of the pond being analyzed", example = "1")
    private Long pondId;
}






