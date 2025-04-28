package com.kbf.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.management.model.FeedUsage;
import com.kbf.management.model.FishStock;
import com.kbf.management.service.FeedUsageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Feed-Usage API")
@RestController
@RequestMapping("/kbf/feed-usage")
public class FeedUsageController {

    @Autowired
    private FeedUsageService service;

    @Operation(summary = "Get all records")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = FishStock.class))),
        @ApiResponse(responseCode = "500", description = "Error")
    })
    @GetMapping
    public ResponseEntity<List<FeedUsage>> getAll() {
        return ResponseEntity.ok(service.getFeedUsages());
    }

    @Operation(summary = "Get by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found", content = @Content(schema = @Schema(implementation = FeedUsage.class))),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FeedUsage> getById(@PathVariable Long id) {
        return service.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create")
    @ApiResponse(responseCode = "200", description = "Created", content = @Content(schema = @Schema(implementation = FeedUsage.class)))
    @PostMapping
    public ResponseEntity<FeedUsage> create(@RequestBody FeedUsage obj) {
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
