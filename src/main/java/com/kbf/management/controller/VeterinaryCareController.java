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

import com.kbf.management.dto.VeterinaryCareDto;
import com.kbf.management.model.VeterinaryCare;
import com.kbf.management.service.VeterinaryCareService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Veterinary API")
@RestController
@RequestMapping("/kbf/veterinary")
@Validated
@RequiredArgsConstructor
public class VeterinaryCareController {

    private final VeterinaryCareService service;

    @Operation(summary = "Retrieve all veterinary care records")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = VeterinaryCare.class))),
        @ApiResponse(responseCode = "500", description = "Error")
    })
    @GetMapping
    public ResponseEntity<List<VeterinaryCareDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get a veterinary care record by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found", content = @Content(schema = @Schema(implementation = VeterinaryCare.class))),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VeterinaryCareDto> getById(@PathVariable Long id) {
    	 return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Create a new veterinary care record")
    @ApiResponse(responseCode = "200", description = "Created", content = @Content(schema = @Schema(implementation = VeterinaryCareDto.class)))
    @PostMapping
    public ResponseEntity<VeterinaryCareDto> create(@RequestBody VeterinaryCareDto dto) {
    	  return ResponseEntity.ok(service.create(dto));
    }
    
    @Operation(summary = "Update an existing veterinary care record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "veterinary care updated"),
        @ApiResponse(responseCode = "400", description = "Invalid data or related entity missing"),
        @ApiResponse(responseCode = "404", description = "veterinary care not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VeterinaryCareDto> update(
            @PathVariable Long id,
            @Valid @RequestBody VeterinaryCareDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Delete a veterinary care record")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "veterinary care record deleted"),
        @ApiResponse(responseCode = "404", description = "veterinary care record not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

   
    
}
