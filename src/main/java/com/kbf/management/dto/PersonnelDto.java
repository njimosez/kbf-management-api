package com.kbf.management.dto;


import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Personnel records")
public class PersonnelDto {

    @Schema(description = "Personnel ID", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Full name of the staff member", example = "Alice Johnson")
    private String name;

    @Schema(description = "Role or position (e.g., Manager, Technician)", example = "Technician")
    private String role;

    @Schema(description = "Contact information (email or phone)", example = "alice@example.com")
    private String contact;

    @Schema(description = "Work shift (e.g., Morning, Night)", example = "Morning")
    private String shift;

    @Schema(description = "Personnel status (e.g., probation, permanent)", example = "permanent")
    private String status;

    @NotNull
    @Schema(description = "Flag indicating if the personnel is currently active", example = "true")
    private Boolean isActive;

    @PositiveOrZero
    @Schema(description = "Basic salary for the personnel", example = "3500.00")
    private double salary;

    @PositiveOrZero
    @Schema(description = "Savings deducted or accrued", example = "200.00")
    private double savings;

    @PositiveOrZero
    @Schema(description = "Net perceived pay after deductions/additions", example = "3300.00")
    private double netPerceived;

    @Schema(description = "IDs of payroll records associated with this personnel")
    private List<Long> payrollIds;
}






