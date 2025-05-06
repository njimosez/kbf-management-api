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

import com.kbf.management.dto.WaterTreatmentDto;
import com.kbf.management.service.WaterTreatmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Water Treatment", description = "Water Treatment API")
@RestController
@RequestMapping("/kbf/watertreatments")
@Validated
@RequiredArgsConstructor
public class WaterTreatmentController {

    private final WaterTreatmentService service;

    @Operation(summary = "Retrieve all water treatment events")
    @ApiResponse(responseCode = "200", description = "List of water treatments retrieved successfully")
    @GetMapping
    public ResponseEntity<List<WaterTreatmentDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get a water treatment event by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Water treatment retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Water treatment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<WaterTreatmentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Create a new water treatment event")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Water treatment created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data or pond not found")
    })
    @PostMapping
    public ResponseEntity<WaterTreatmentDto> create(@Valid @RequestBody WaterTreatmentDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Operation(summary = "Update an existing water treatment event")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Water treatment updated successfully"),
        @ApiResponse(responseCode = "404", description = "Water treatment not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<WaterTreatmentDto> update(
            @PathVariable Long id,
            @Valid @RequestBody WaterTreatmentDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Delete a water treatment event")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Water treatment deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Water treatment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
