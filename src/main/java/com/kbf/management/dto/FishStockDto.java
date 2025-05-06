package com.kbf.management.dto;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for FishStock")
public class FishStockDto {
	
		@Schema(description = "ID of the FishStock ", example = "1")
		private Long fishStockId;
	
	 @PositiveOrZero
	    @Schema(description = "Initial number of fish in this batch", example = "1000")
	    private int initialStock;
	 
	 @PositiveOrZero
	    @Schema(description = "Fish stock after deduction of qty sold, mortality and reduction", example = "500")
	    private int stockRemaining;


	    @PositiveOrZero
	    @Schema(description = "Number of times feed was given per day", example = "2")
	    private int timesFed;

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

	    
	    @Schema(description = "Growth stage (e.g., Fingerling, Grower, Adult)", example = "Fingerling")
	    private String stage;
	    
	    @Schema(description = "Specy of the Fish", example = "Clarias")
	    private String specy;


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







