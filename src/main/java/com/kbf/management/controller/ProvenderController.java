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

import com.kbf.management.model.Provender;
import com.kbf.management.service.ProvenderService;

import java.util.List;

@Tag(name = "Provender API")
@RestController
@RequestMapping("/api/provenders")
public class ProvenderController {

    @Autowired
    private ProvenderService service;

    @Operation(summary = "Get all records")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Provender.class))),
        @ApiResponse(responseCode = "500", description = "Error")
    })
    @GetMapping
    public ResponseEntity<List<Provender>> getAll() {
        return ResponseEntity.ok(service.getAllProvenders());
    }

    @Operation(summary = "Get by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found", content = @Content(schema = @Schema(implementation = Provender.class))),
        @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Provender> getById(@PathVariable Long id) {
        return service.getById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create")
    @ApiResponse(responseCode = "200", description = "Created", content = @Content(schema = @Schema(implementation = Provender.class)))
    @PostMapping
    public ResponseEntity<Provender> create(@RequestBody Provender obj) {
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
