package com.kbf.management.dto;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Sample")
public class SampleDto {
	
	 @Schema(description = "Sampling record ID")
	    private Long id;

	    @NotBlank
	    @Schema(description = "Species sampled", example = "Tilapia")
	    private String specy;

	    @NotNull
	    @Schema(description = "Date of sampling", example = "2025-05-03")
	    private LocalDate sampleDate;

	    @PositiveOrZero
	    @Schema(description = "Number of fish sampled", example = "50")
	    private int sampleQty;

	    @PositiveOrZero
	    @Schema(description = "Total weight of sampled fish in grams", example = "1250.5")
	    private double totalSampleWeight;

	    @PositiveOrZero
	    @Schema(description = "Average size of sampled fish in cm", example = "25.0")
	    private double avgFishSize;

	    @PositiveOrZero
	    @Schema(description = "Target fish size in cm", example = "30.0")
	    private double targetSize;

	    @NotNull
	    @Schema(description = "ID of the fish stock batch sampled", example = "1")
	    private Long fishStockId;
	
	}







