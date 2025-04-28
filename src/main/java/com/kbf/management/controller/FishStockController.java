package com.kbf.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kbf.management.model.FishStock;
import com.kbf.management.service.FishStockService;

import java.util.List;

@Tag(name = "FishStock API")
@RestController
@RequestMapping("/kbf/fishstocks")
public class FishStockController {

    @Autowired
    private FishStockService service;

    @Operation(summary = "Get all records")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = FishStock.class))),
        @ApiResponse(responseCode = "500", description = "Error")
    })
    @GetMapping
    public ResponseEntity<List<FishStock>> getAll() {
        return ResponseEntity.ok(service.getFishStocks());
    }

    @Operation(summary = "Get by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found", content = @Content(schema = @Schema(implementation = FishStock.class))),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FishStock> getById(@PathVariable Long id) {
        return service.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create")
    @ApiResponse(responseCode = "200", description = "Created", content = @Content(schema = @Schema(implementation = FishStock.class)))
    @PostMapping
    public ResponseEntity<FishStock> create(@RequestBody FishStock obj) {
        return ResponseEntity.ok(service.save(obj));
    }

    @Operation(summary = "Delete")
    @ApiResponse(responseCode = "204", description = "Deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
