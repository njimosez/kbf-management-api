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
import com.kbf.management.model.Transaction;
import com.kbf.management.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Transaction API")
@RestController
@RequestMapping("/kbf/transactions")
@Validated
@RequiredArgsConstructor
public class TransactionController {

	 private final TransactionService transactionService;

	    @Operation(summary = "Get all transactions")
	    @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully")
	    @GetMapping
	    public ResponseEntity<List<Transaction>> getAll() {
	        return ResponseEntity.ok(transactionService.getAll());
	    }

	    @Operation(summary = "Get a transaction by its ID")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Transaction found"),
	        @ApiResponse(responseCode = "404", description = "Transaction not found")
	    })
	    @GetMapping("/{id}")
	    public ResponseEntity<Transaction> getById(@PathVariable Long id) {
	        return ResponseEntity.ok(transactionService.getById(id));
	    }
	    
	    @Operation(
	            summary = "Get transactions from last X months",
	            description = "Fetches all transactions starting from (current date - given months) to today, ordered by date ascending"
	        )
	    @GetMapping("/last/{months}")
	    public List<Transaction> getTransactionsFromLastMonths(@PathVariable int months) {
	        if (months <= 0) {
	            throw new IllegalArgumentException("Months must be greater than 0");
	        }
	        return transactionService.getTransactionsFromLastMonths(months);
	    }

	    @Operation(summary = "Create a new transaction and update related entity")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Transaction created"),
	        @ApiResponse(responseCode = "400", description = "Invalid data or related entity missing")
	    })
	    @PostMapping
	    public ResponseEntity<Transaction> create(
	            @Valid @RequestBody TransactionDto dto) {
	        return ResponseEntity.ok(transactionService.createTransaction(dto));
	    }

	    @Operation(summary = "Update an existing transaction and reapply business logic")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Transaction updated"),
	        @ApiResponse(responseCode = "400", description = "Invalid data or related entity missing"),
	        @ApiResponse(responseCode = "404", description = "Transaction not found")
	    })
	    @PutMapping("/{id}")
	    public ResponseEntity<Transaction> update(
	            @PathVariable Long id,
	            @Valid @RequestBody TransactionDto dto) {
	        return ResponseEntity.ok(transactionService.updateTransaction(id, dto));
	    }

	    @Operation(summary = "Delete a transaction")
	    @ApiResponses({
	        @ApiResponse(responseCode = "204", description = "Transaction deleted"),
	        @ApiResponse(responseCode = "404", description = "Transaction not found")
	    })
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        transactionService.deleteTransaction(id);
	        return ResponseEntity.noContent().build();
	    }
	}