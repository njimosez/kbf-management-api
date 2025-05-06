package com.kbf.management.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for Pond")
public class PondDto {
	
	@Schema(description = "ID of the pond where this stock resides", example = "1")
    private Long pondId;
	
	@NotBlank
    @Schema(description = "Name of the pond", example = "Pond A")
    private String name;

    @Positive
    @Schema(description = "Size of the pond in square meters", example = "500.0")
    private double size;

    @Schema(description = "Location description", example = "North Farm Sector")
    private String location;

    @Schema(description = "Current status of the pond", example = "Active")
    private String status;

    @Min(0)
    @Schema(description = "Maximum fish capacity", example = "1000")
    private int fishCapacity;
	
	}







