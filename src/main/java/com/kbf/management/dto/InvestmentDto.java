package com.kbf.management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.kbf.management.model.Investment.DepreciationMethod;

@Data
@Schema(description = "Data transfer object for recording a capital investment (asset purchase)")
public class InvestmentDto {
	
	@NotBlank
    @Schema(description = "Human-readable name for the investment", example = "New Pond Excavation")
    private String name;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true)
    @Schema(description = "Total cost of the asset at acquisition", example = "15000.00")
    private BigDecimal acquisitionCost;

    @NotNull
    @Schema(description = "Date when the asset was acquired", example = "2025-05-01")
    private LocalDate acquisitionDate;

    @Min(1)
    @Schema(description = "Expected useful life of the asset in years", example = "10")
    private int usefulLifeYears;

    @DecimalMin(value = "0.00", inclusive = true)
    @Schema(description = "Estimated salvage (residual) value at end of life", example = "1000.00")
    private BigDecimal salvageValue;

    @NotNull
    @Schema(
      description = "Depreciation method to apply",
      allowableValues = {"STRAIGHT_LINE","DOUBLE_DECLINING_BALANCE"},
      example = "STRAIGHT_LINE"
    )
    private DepreciationMethod depreciationMethod;
}
