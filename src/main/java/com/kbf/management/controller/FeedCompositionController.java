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

import com.kbf.management.dto.FeedCompositionDto;
import com.kbf.management.service.FeedCompositionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Tag(name = "FeedComposition", description = "FeedComposition API")
@RestController
@RequestMapping("/kbf/feedcompositions")
@Validated
@RequiredArgsConstructor
public class FeedCompositionController {

	private final FeedCompositionService service;

    @Operation(summary = "Retrieve all feed composition entries")
    @ApiResponse(responseCode = "200", description = "List of compositions retrieved")
    @GetMapping
    public ResponseEntity<List<FeedCompositionDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Get a feed composition entry by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Composition retrieved"),
        @ApiResponse(responseCode = "404", description = "Composition not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FeedCompositionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Create a new feed composition entry")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Composition created"),
        @ApiResponse(responseCode = "400", description = "Invalid input or provender/ingredient not found")
    })
    @PostMapping
    public ResponseEntity<FeedCompositionDto> create(@Valid @RequestBody FeedCompositionDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Operation(summary = "Update an existing feed composition entry")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Composition updated"),
        @ApiResponse(responseCode = "404", description = "Composition not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FeedCompositionDto> update(
            @PathVariable Long id,
            @Valid @RequestBody FeedCompositionDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Delete a feed composition entry")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Composition deleted"),
        @ApiResponse(responseCode = "404", description = "Composition not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}