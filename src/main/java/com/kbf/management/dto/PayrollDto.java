package com.kbf.management.dto;


import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Payroll records")
public class PayrollDto {

    @Schema(description = "Payroll record ID", example = "1")
    private Long id;

    @NotNull
    @Positive
    @Schema(description = "Personnel ID associated with payroll", example = "3")
    private Long personnelId;

    @DecimalMin(value = "0.0", inclusive = true)
    @Schema(description = "Base salary amount", example = "3000.00")
    private double baseSalary;

    @DecimalMin(value = "0.0", inclusive = true)
    @Schema(description = "Bonus amount", example = "500.00")
    private double bonus;

    @DecimalMin(value = "0.0", inclusive = true)
    @Schema(description = "Deduction amount", example = "100.00")
    private double deduction;

    @DecimalMin(value = "0.0", inclusive = true)
    @Schema(description = "Net pay after calculations", example = "3400.00")
    private double netPay;

    @NotNull
    @Schema(description = "Date of payment", example = "2025-05-12")
    private LocalDate paymentDate;

    @NotBlank
    @Schema(description = "Method of payment (e.g., Bank transfer, Cash)", example = "Bank Transfer")
    private String paymentMethod;

    @Schema(description = "IDs of related transactions")
    private List<Long> transactionIds;
}






