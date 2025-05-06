package com.kbf.management.dto;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Feed Usage records")
public class FeedUsageDto {
	
	@Schema(description = "Feed usage record ID", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Type of feed used (e.g., Starter, Grower)", example = "Starter Feed")
    private String feedType;
    
    @PositiveOrZero
    @Schema(description = "Number of times feed was given per day", example = "2")
    private int timesFed;
    
    
    @PositiveOrZero
    @Schema(description = "Quantity of feed used in kg", example = "2.5")
    private double quantityUsed;
    
    @PositiveOrZero
    @Schema(description = "Average size of sampled fish in grams", example = "25.0")
    private double avgFishSize;

    @NotNull
    @Schema(description = "Date when feed was applied", example = "2025-05-04")
    private LocalDate usageDate;

    @PositiveOrZero
    @Schema(description = "Number of fish mortalities observed during feeding", example = "0")
    private int mortality;

    @PositiveOrZero
    @Schema(description = "Number of fish removed or reduced by other means", example = "5")
    private int reduced;

    @NotNull
    @Positive
    @Schema(description = "ID of the fish stock batch receiving this feed", example = "1")
    private Long fishStockId;

    @NotNull
    @Positive
    @Schema(description = "ID of the provender batch used", example = "1")
    private Long provenderId;
	}







