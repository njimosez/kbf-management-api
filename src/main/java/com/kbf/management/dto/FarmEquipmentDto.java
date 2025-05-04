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
@Schema(description = "Data transfer object for FarmEquipment records")
public class FarmEquipmentDto {

    @Schema(description = "Equipment ID", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Name of the equipment", example = "Water Pump X100")
    private String name;

    @Schema(description = "Type or category", example = "Pump")
    private String equipmentType;

    @Schema(description = "Manufacturer of the equipment", example = "AquaTech")
    private String manufacturer;

    @Min(0)
    @Schema(description = "Quantity available", example = "5")
    private int qty;

    @NotNull
    @Schema(description = "Purchase date", example = "2025-04-01")
    private LocalDate purchaseDate;

    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Cost of purchase", example = "1200.00")
    private double cost;

    @Schema(description = "Maintenance schedule details", example = "Monthly inspection")
    private String maintenanceSchedule;

    @Schema(description = "Physical location on farm", example = "North Pond")
    private String location;

    @Schema(description = "Current status", example = "Operational")
    private String status;

    @Schema(description = "Transaction ID for purchase record", example = "10")
    private Long purchaseTransactionId;

    @Schema(description = "IDs of maintenance records")
    private List<Long> maintenanceRecordIds;
}





