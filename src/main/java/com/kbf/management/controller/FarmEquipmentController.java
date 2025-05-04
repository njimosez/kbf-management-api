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

import com.kbf.management.dto.FarmEquipmentDto;
import com.kbf.management.service.FarmEquipmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Farm Equipment API")
@RestController
@RequestMapping("/kbf/farm-equipments")
@RequiredArgsConstructor
public class FarmEquipmentController {

	 private final FarmEquipmentService farmEquipmentService;

	    @Operation(summary = "Get all farm equipments")
	    @ApiResponse(responseCode = "200", description = "farm equipments retrieved successfully")
	    @GetMapping
	    public ResponseEntity<List<FarmEquipmentDto>> getAll() {
	        return ResponseEntity.ok(farmEquipmentService.getAll());
	    }

	    @Operation(summary = "Get a farm equipment  by ID")
	    @ApiResponse(responseCode = "200", description = "farm equipment  retrieved successfully")
	    @GetMapping("/{id}")
	    public ResponseEntity<FarmEquipmentDto> getById(@PathVariable Long id) {
	        return ResponseEntity.ok(farmEquipmentService.getById(id));
	    }

	    @Operation(summary = "Create a new farm equipment  record")
	    @ApiResponse(responseCode = "200", description = "farm equipment  created successfully")
	    @PostMapping
	    public ResponseEntity<FarmEquipmentDto> create(@Valid @RequestBody FarmEquipmentDto dto) {
	        return ResponseEntity.ok(farmEquipmentService.create(dto));
	    }

	    @Operation(summary = "Update an existing farm equipment  record")
	    @ApiResponse(responseCode = "200", description = "farm equipment  updated successfully")
	    @PutMapping("/{id}")
	    public ResponseEntity<FarmEquipmentDto> update(
	            @PathVariable Long id,
	            @Valid @RequestBody FarmEquipmentDto dto) {
	        return ResponseEntity.ok(farmEquipmentService.update(id, dto));
	    }

	    @Operation(summary = "Delete a farm equipment  record")
	    @ApiResponse(responseCode = "204", description = "farm equipment  deleted successfully")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        farmEquipmentService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	}
