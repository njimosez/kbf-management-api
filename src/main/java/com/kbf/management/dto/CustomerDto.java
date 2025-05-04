package com.kbf.management.dto;


import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Customer records")
public class CustomerDto {

    @Schema(description = "Customer ID", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Customer name or company", example = "Fish Buyers LLC")
    private String name;

    @Schema(description = "Contact person or department", example = "Jane Doe")
    private String contact;

    @Schema(description = "Physical address", example = "456 Market Street")
    private String address;

    @Email
    @Schema(description = "Email address", example = "sales@fishbuyers.com")
    private String email;

    @Schema(description = "Phone number", example = "+1-555-5678")
    private String phone;

    @Schema(description = "IDs of related transactions")
    private List<Long> transactionIds;
}






