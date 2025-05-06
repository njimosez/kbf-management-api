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

import com.kbf.management.dto.ProvenderDto;
import com.kbf.management.service.ProvenderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Provender", description = "Provender API")
@RestController
@RequestMapping("/kbf/provenders")
@Validated
@RequiredArgsConstructor
public class ProvenderController {


    private final ProvenderService provenderService;

    @Operation(summary = "Retrieve all provender batches")
    @ApiResponse(responseCode = "200", description = "List of provender batches retrieved successfully")
    @GetMapping
    public ResponseEntity<List<ProvenderDto>> getAll() {
        return ResponseEntity.ok(provenderService.getAll());
    }

    @Operation(summary = "Get a provender batch by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Provender batch retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Provender batch not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProvenderDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(provenderService.getById(id));
    }

    @Operation(summary = "Create a new provender batch")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Provender batch created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data or supplier not found")
    })
    @PostMapping
    public ResponseEntity<ProvenderDto> create(@Valid @RequestBody ProvenderDto dto) {
        return ResponseEntity.ok(provenderService.create(dto));
    }

    @Operation(summary = "Update an existing provender batch")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Provender batch updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data or supplier not found"),
        @ApiResponse(responseCode = "404", description = "Provender batch not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProvenderDto> update(
            @PathVariable Long id,
            @Valid @RequestBody ProvenderDto dto) {
        return ResponseEntity.ok(provenderService.update(id, dto));
    }

    @Operation(summary = "Delete a provender batch")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Provender batch deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Provender batch not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        provenderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
