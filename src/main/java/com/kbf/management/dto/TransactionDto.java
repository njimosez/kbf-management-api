package com.kbf.management.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.kbf.management.model.OperationType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for creating or updating a Transaction")
public class TransactionDto {

//    @NotBlank
//    @Schema(description = "Type of transaction", example = "SALE")
//    private String type;
	
	@Schema(description = "Transaction ID", example = "1")
    private Long id;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Monetary amount of the transaction", example = "150.00")
    private BigDecimal amount;

    @Size(max = 255)
    @Schema(description = "Optional description or notes", example = "Sold 200 tilapia fingerlings")
    private String description;
    
   

    @Schema(description = "Date of the transaction (defaults to today if omitted)", example = "2025-05-02")
    private LocalDate date;
    
    @Schema(description = "Which type entity this transaction is related to" , example = "INCOME or EXPENSES")
    private String transactionType;
    
    private String categoryType;
     // This should be moved to the respective dtos
     private int soldQty; 
     private int qtyInKg;
     private int qtyPurchased; //in kg for provender and probiotic
     private int unitPrice;
     private int charges ; // transport, tax...
    
 

    @NotNull
    @Schema(
      description = "Which entity this transaction is related to",
      allowableValues = {"FISH_STOCK","PROVENDER","PAYROLL","PROBIOTIC","FARM_EQUIPMENT"}
    )
    private OperationType operationType;

    @Positive
    @Schema(description = "ID of the FishStock (if categoryType = FISH_STOCK)", example = "1")
    private Long fishStockId;

    @Positive
    @Schema(description = "ID of the Provender (if categoryType = PROVENDER)", example = "2")
    private Long provenderId;

    @Positive
    @Schema(description = "ID of the Payroll record (if categoryType = PAYROLL)", example = "3")
    private Long payrollId;
    
    @Schema(description = "Payroll data object for the transaction")
    private PayrollDto payrollDto;

    @Positive
    @Schema(description = "ID of the ProbioticApplication (if categoryType = PROBIOTIC)", example = "4")
    private Long probioticId;
    
    private ProbioticDto probioticDto;

    @Positive
    @Schema(description = "ID of the FarmEquipment (if categoryType = FARM_EQUIPMENT)", example = "5")
    private Long equipmentId;
    
    private FarmEquipmentDto farmEquipmentDto;
    
    @Positive
    @Schema(description = "ID of the Customer (if categoryType = CUSTOMER)", example = "10")
    private Long customerId;
    
    private ProvenderDto provenderDto;
    
    @Positive
    @Schema(description = "ID of the capital investment", example = "10")
    private Long investmentId;
    
    private InvestmentDto InvestmentDto;

	
    
}
