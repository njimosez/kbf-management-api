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

import com.kbf.management.dto.TransactionDto;
import com.kbf.management.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Transaction", description = "Transaction API")
@RestController
@RequestMapping("/kbf/transactions")
@Validated
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService service;

    @Operation(summary = "Retrieve all transactions")
    @ApiResponse(responseCode = "200", description = "List of transactions retrieved successfully")
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    @Operation(
            summary = "Get transactions from last X months",
            description = "Fetches all transactions starting from (current date - given months) to today, ordered by date ascending"
        )
    @GetMapping("/last/{months}")
    public ResponseEntity<List<TransactionDto>> getTransactionsFromLastMonths(@PathVariable int months) {
        if (months <= 0) {
            throw new IllegalArgumentException("Months must be greater than 0");
        }
        return ResponseEntity.ok(service.getTransactionsFromLastMonths(months)) ;
    }

    @Operation(summary = "Get a transaction by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Create a new transaction")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid transaction data")
    })
    @PostMapping
    public ResponseEntity<TransactionDto> create(@Valid @RequestBody TransactionDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Operation(summary = "Create multiple transactions")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transactions created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid transaction data in list")
    })
    @PostMapping("/batch")
    public ResponseEntity<List<TransactionDto>> createAll(@Valid @RequestBody List<TransactionDto> dtos) {
        return ResponseEntity.ok(service.createAll(dtos));
    }

    @Operation(summary = "Update an existing transaction")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transaction updated successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> update(
            @PathVariable Long id,
            @Valid @RequestBody TransactionDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Delete a transaction")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Transaction deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
