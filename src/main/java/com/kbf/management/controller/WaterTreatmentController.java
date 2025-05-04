package com.kbf.management.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kbf.management.dto.WaterTreatmentDto;
import com.kbf.management.model.WaterTreatment;
import com.kbf.management.service.WaterTreatmentService;

import java.util.List;

@Tag(name = "WaterTreatment API")
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
