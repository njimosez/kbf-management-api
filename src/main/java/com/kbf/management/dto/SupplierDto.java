package com.kbf.management.dto;


import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Supplier records")
public class SupplierDto {

    @Schema(description = "Supplier ID", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Supplier name", example = "Aqua Supplies Ltd.")
    private String name;

    @Schema(description = "Contact person or department", example = "John Doe")
    private String contact;

    @Schema(description = "Physical address", example = "123 Fish Farm Road")
    private String address;

    @Email
    @Schema(description = "Email address", example = "contact@aquasupplies.com")
    private String email;

    @Schema(description = "Phone number", example = "+1-555-1234")
    private String phone;

    @Schema(description = "IDs of provender batches supplied")
    private List<Long> provenderIds;

    @Schema(description = "IDs of probiotic applications sourced")
    private List<Long> probioticApplicationIds;

    @Schema(description = "IDs of related transactions")
    private List<Long> transactionIds;
}







