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

import com.kbf.management.dto.SampleDto;
import com.kbf.management.service.SampleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Sample API")
@RestController
@RequestMapping("/kbf/samples")
@RequiredArgsConstructor
public class SampleController {

	 private final SampleService sampleService;

	    @Operation(summary = "Get all samples")
	    @ApiResponse(responseCode = "200", description = "Samples retrieved successfully")
	    @GetMapping
	    public ResponseEntity<List<SampleDto>> getAll() {
	        return ResponseEntity.ok(sampleService.getAll());
	    }

	    @Operation(summary = "Get a sample by ID")
	    @ApiResponse(responseCode = "200", description = "Sample retrieved successfully")
	    @GetMapping("/{id}")
	    public ResponseEntity<SampleDto> getById(@PathVariable Long id) {
	        return ResponseEntity.ok(sampleService.getById(id));
	    }

	    @Operation(summary = "Create a new sample record")
	    @ApiResponse(responseCode = "200", description = "Sample created successfully")
	    @PostMapping
	    public ResponseEntity<SampleDto> create(@Valid @RequestBody SampleDto dto) {
	        return ResponseEntity.ok(sampleService.create(dto));
	    }

	    @Operation(summary = "Update an existing sample record")
	    @ApiResponse(responseCode = "200", description = "Sample updated successfully")
	    @PutMapping("/{id}")
	    public ResponseEntity<SampleDto> update(
	            @PathVariable Long id,
	            @Valid @RequestBody SampleDto dto) {
	        return ResponseEntity.ok(sampleService.update(id, dto));
	    }

	    @Operation(summary = "Delete a sample record")
	    @ApiResponse(responseCode = "204", description = "Sample deleted successfully")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        sampleService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	}
