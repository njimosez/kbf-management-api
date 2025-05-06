package com.kbf.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.management.dto.WaterAnalysisDto;
import com.kbf.management.service.WaterAnalysisService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Water Analysis", description = "H2O Analysis API")
@RestController
@RequestMapping("/kbf/water-analysis")
@Validated
@RequiredArgsConstructor
public class WaterAnalysisController {

	private final WaterAnalysisService service;

	@Operation(summary = "Retrieve all water analyses")
	@ApiResponse(responseCode = "200", description = "List of water analyses retrieved successfully")
	@GetMapping
	public ResponseEntity<List<WaterAnalysisDto>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@Operation(summary = "Get a water analysis by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Water analysis retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Water analysis not found") })
	@GetMapping("/{id}")
	public ResponseEntity<WaterAnalysisDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}

	@Operation(summary = "Create a new water analysis record")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Water analysis created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data or pond not found") })
	@PostMapping
	public ResponseEntity<WaterAnalysisDto> create(@Valid @RequestBody WaterAnalysisDto dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@Operation(summary = "Update an existing water analysis record")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Water analysis updated successfully"),
			@ApiResponse(responseCode = "404", description = "Water analysis not found") })
	@PutMapping("/{id}")
	public ResponseEntity<WaterAnalysisDto> update(@PathVariable Long id, @Valid @RequestBody WaterAnalysisDto dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}

	@Operation(summary = "Delete a water analysis record")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Water analysis deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Water analysis not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
