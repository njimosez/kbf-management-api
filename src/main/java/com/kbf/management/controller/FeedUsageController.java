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

import com.kbf.management.dto.FeedUsageDto;
import com.kbf.management.service.FeedUsageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Feed-Usage API")
@RestController
@RequestMapping("/kbf/feed-usage")
@Validated
@RequiredArgsConstructor
public class FeedUsageController {

	private final FeedUsageService feedUsageService;

    @Operation(summary = "Retrieve all feed usage records")
    @ApiResponse(responseCode = "200", description = "Feed usage list retrieved successfully")
    @GetMapping
    public ResponseEntity<List<FeedUsageDto>> getAll() {
        return ResponseEntity.ok(feedUsageService.getAll());
    }

    @Operation(summary = "Get a feed usage record by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Feed usage retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Feed usage not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FeedUsageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(feedUsageService.getById(id));
    }

    @Operation(summary = "Create a new feed usage record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Feed usage created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<FeedUsageDto> create(@Valid @RequestBody FeedUsageDto dto) {
        return ResponseEntity.ok(feedUsageService.create(dto));
    }

    @Operation(summary = "Update an existing feed usage record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Feed usage updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Feed usage not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FeedUsageDto> update(
            @PathVariable Long id,
            @Valid @RequestBody FeedUsageDto dto) {
        return ResponseEntity.ok(feedUsageService.update(id, dto));
    }

    @Operation(summary = "Delete a feed usage record")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Feed usage deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Feed usage not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedUsageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
