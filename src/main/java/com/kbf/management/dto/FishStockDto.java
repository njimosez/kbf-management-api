package com.kbf.management.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


import java.time.LocalDate;

@Data
@Schema(description = "Data transfer object for FishStock")
public class FishStockDto {
	
	 @PositiveOrZero
	    @Schema(description = "Initial number of fish in this batch", example = "1000")
	    private int initialStock;

	    @PositiveOrZero
	    @Schema(description = "Current remaining stock after sales and mortality", example = "850")
	    private int stockRemaining;

	    @PositiveOrZero
	    @Schema(description = "Number of fish sold to date", example = "150")
	    private int qtySold;

	    @PositiveOrZero
	    @Schema(description = "Number of fish lost due to mortality", example = "10")
	    private int mortality;

	    @PositiveOrZero
	    @Schema(description = "Number of fish removed or reduced by other means", example = "5")
	    private int reduction;

	    @Schema(description = "Flag indicating whether this batch is sold out", example = "false")
	    private boolean isSoldOut;

	    @NotBlank
	    @Schema(description = "Growth stage (e.g., Fingerling, Grower, Adult)", example = "Fingerling")
	    private String stage;

	    @NotNull
	    @Schema(description = "Date when this stock was added", example = "2025-05-02")
	    private LocalDate dateAdded;

	    @NotBlank
	    @Schema(description = "Batch identifier or code", example = "BATCH-202505")
	    private String batch;

	    @NotNull
	    @Positive
	    @Schema(description = "ID of the pond where this stock resides", example = "1")
	    private Long pondId;
	}







