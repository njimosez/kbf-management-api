package com.kbf.management.dto;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for MaintenanceRecord records")
public class MaintenanceRecordDto {

    @Schema(description = "Maintenance record ID", example = "1")
    private Long id;

    @NotNull
    @Positive
    @Schema(description = "ID of the equipment maintained", example = "2")
    private Long equipmentId;

    @NotNull
    @Schema(description = "Date of maintenance", example = "2025-06-01")
    private LocalDate maintenanceDate;

    @NotBlank
    @Schema(description = "Description of work performed", example = "Replaced filter and cleaned motor")
    private String description;

    @DecimalMin(value = "0.0", inclusive = true)
    @Schema(description = "Cost of maintenance", example = "150.00")
    private double cost;

    @Schema(description = "Name of person who performed maintenance", example = "Technician Joe")
    private String performedBy;

    @Schema(description = "Additional notes or observations", example = "Next service due in 6 months")
    private String notes;
}


