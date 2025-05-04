package com.kbf.management.dto;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Feed Composition entries")
public class FeedCompositionDto {
	
	@Schema(description = "Composition entry ID", example = "1")
    private Long id;

    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Percentage of ingredient in feed mix", example = "40.0")
    private double percentage;

    @PositiveOrZero
    @Schema(description = "Protein content contribution (g per kg)", example = "200.0")
    private double protein;

    @PositiveOrZero
    @Schema(description = "Fat content contribution (g per kg)", example = "50.0")
    private double fat;

    @PositiveOrZero
    @Schema(description = "Fiber content contribution (g per kg)", example = "10.0")
    private double fiber;

    @PositiveOrZero
    @Schema(description = "Energy contribution (kcal per kg)", example = "3500.0")
    private double energy;

    @Schema(description = "Flag indicating if premix is included", example = "true")
    private boolean premix;

    @DecimalMin(value = "0.0", inclusive = true)
    @Schema(description = "Moisture content percentage", example = "12.5")
    private double moistureContent;

    @DecimalMin(value = "0.0", inclusive = true)
    @Schema(description = "Ash percentage", example = "8.0")
    private double ashPercentage;

    @NotNull
    @Positive
    @Schema(description = "ID of the Ingredient", example = "2")
    private Long ingredientId;

    @NotNull
    @Positive
    @Schema(description = "ID of the Provender batch", example = "1")
    private Long provenderId;
}






