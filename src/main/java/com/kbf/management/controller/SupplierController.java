package com.kbf.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.management.dto.SupplierDto;
import com.kbf.management.service.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Supplier API")
@RestController
@RequestMapping("/kbf/suppliers")
@RequiredArgsConstructor
public class SupplierController {

	 private final SupplierService supplierService;

	    @Operation(summary = "Get all suppliers")
	    @ApiResponse(responseCode = "200", description = "suppliers retrieved successfully")
	    @GetMapping
	    public ResponseEntity<List<SupplierDto>> getAll() {
	        return ResponseEntity.ok(supplierService.getAll());
	    }

	    @Operation(summary = "Get a supplier by ID")
	    @ApiResponse(responseCode = "200", description = "supplier retrieved successfully")
	    @GetMapping("/{id}")
	    public ResponseEntity<SupplierDto> getById(@PathVariable Long id) {
	        return ResponseEntity.ok(supplierService.getById(id));
	    }

	    @Operation(summary = "Create a new supplier record")
	    @ApiResponse(responseCode = "200", description = "supplier created successfully")
	    @PostMapping
	    public ResponseEntity<SupplierDto> create(@Valid @RequestBody SupplierDto dto) {
	        return ResponseEntity.ok(supplierService.create(dto));
	    }

	    @Operation(summary = "Update an existing supplier record")
	    @ApiResponse(responseCode = "200", description = "supplier updated successfully")
	    @PutMapping("/{id}")
	    public ResponseEntity<SupplierDto> update(
	            @PathVariable Long id,
	            @Valid @RequestBody SupplierDto dto) {
	        return ResponseEntity.ok(supplierService.update(id, dto));
	    }

	    @Operation(summary = "Delete a supplier record")
	    @ApiResponse(responseCode = "204", description = "supplier deleted successfully")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        supplierService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	}
