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

import com.kbf.management.dto.PondDto;
import com.kbf.management.service.PondService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Pond API")
@RestController
@RequestMapping("/kbf/ponds")
@Validated
@RequiredArgsConstructor
public class PondController {

	private final PondService pondService;

    @Operation(summary = "Retrieve all ponds")
    @ApiResponse(responseCode = "200", description = "Ponds retrieved successfully")
    @GetMapping
    public ResponseEntity<List<PondDto>> getAll() {
        return ResponseEntity.ok(pondService.getAll());
    }

    @Operation(summary = "Get a pond by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pond retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Pond not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PondDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pondService.getById(id));
    }

    @Operation(summary = "Create a new pond")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pond created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<PondDto> create(@Valid @RequestBody PondDto dto) {
        return ResponseEntity.ok(pondService.create(dto));
    }

    @Operation(summary = "Update an existing pond")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pond updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Pond not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PondDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PondDto dto) {
        return ResponseEntity.ok(pondService.update(id, dto));
    }

    @Operation(summary = "Delete a pond")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pond deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Pond not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pondService.delete(id);
        return ResponseEntity.noContent().build();
    }
}