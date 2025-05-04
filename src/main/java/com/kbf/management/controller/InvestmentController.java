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

import com.kbf.management.dto.InvestmentDto;
import com.kbf.management.model.Investment;
import com.kbf.management.service.InvestmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/investments")
@Validated
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService investmentService;

    @Operation(summary = "Retrieve all investments")
    @ApiResponse(responseCode = "200", description = "Investments retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Investment>> getAll() {
        List<Investment> investments = investmentService.getAll();
        return ResponseEntity.ok(investments);
    }

    @Operation(summary = "Get an investment by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Investment found"),
        @ApiResponse(responseCode = "404", description = "Investment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Investment> getById(@PathVariable Long id) {
        Investment inv = investmentService.getById(id);
        return ResponseEntity.ok(inv);
    }

    @Operation(summary = "Create a new investment record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Investment created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<Investment> create(@Valid @RequestBody InvestmentDto dto) {
        Investment created = investmentService.create(dto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Update an existing investment record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Investment updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Investment not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Investment> update(
            @PathVariable Long id,
            @Valid @RequestBody InvestmentDto dto) {
        Investment updated = investmentService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete an investment record")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Investment deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Investment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        investmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}