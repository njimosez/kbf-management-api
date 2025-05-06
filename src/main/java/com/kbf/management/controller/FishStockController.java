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

import com.kbf.management.dto.FishStockDto;
import com.kbf.management.service.FishStockService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "FishStock", description = "FishStock API")
@RestController
@RequestMapping("/kbf/fishstocks")
@Validated
@RequiredArgsConstructor
public class FishStockController {

	private final FishStockService fishStockService;

	@Operation(summary = "Get all fish stock records")
	@ApiResponse(responseCode = "200", description = "Fish stock list retrieved successfully")
	@GetMapping
	public ResponseEntity<List<FishStockDto>> getAll() {
		return ResponseEntity.ok(fishStockService.getAll());
	}

	@Operation(summary = "Get a fish stock record by its ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Fish stock retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Fish stock not found") })
	@GetMapping("/{id}")
	public ResponseEntity<FishStockDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(fishStockService.getById(id));
	}

	@Operation(summary = "Create a new fish stock record")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Fish stock created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping
	public ResponseEntity<FishStockDto> create(@Valid @RequestBody FishStockDto dto) {
		return ResponseEntity.ok(fishStockService.create(dto));
	}

	@Operation(summary = "Update an existing fish stock record")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Fish stock updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data"),
			@ApiResponse(responseCode = "404", description = "Fish stock not found") })
	@PutMapping("/{id}")
	public ResponseEntity<FishStockDto> update(@PathVariable Long id, @Valid @RequestBody FishStockDto dto) {
		return ResponseEntity.ok(fishStockService.update(id, dto));
	}

	@Operation(summary = "Delete a fish stock record")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Fish stock deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Fish stock not found") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		fishStockService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
