package com.kbf.management.dto;


import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Provender (feed inventory)")
public class ProvenderDto {
	
	@NotBlank
    @Schema(description = "Feed type name", example = "Grower Mix")
    private String feedType;

    @Min(value = 0, message = "Quantity must be zero or positive")
    @Schema(description = "Quantity in stock (kg)", example = "250.0")
    private double quantity;

    @Schema(description = "Date when this batch was last restocked", example = "2025-05-04")
    private LocalDate lastRestocked;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price per kg must be non-negative")
    @Schema(description = "Price per kilogram", example = "25.50")
    private double pricePerKg;

    @Schema(description = "Expiry date of this feed batch", example = "2025-12-31")
    private LocalDate expiryDate;

    @Schema(description = "Feeding recommendations or notes", example = "Feed twice daily at dawn and dusk")
    private String feedingNotes;

    @Schema(description = "Manufacturer of the feed", example = "FeedCorp International")
    private String manufacturer;


    @NotNull
    @Schema(description = "ID of the supplier for this feed batch", example = "3")
    private Long supplierId;

    @Schema(description = "List of feed composition entries")
    private List<FeedCompositionDto> feedCompositions;
}






