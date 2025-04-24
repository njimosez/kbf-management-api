package com.kbf.management.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kbf.management.model.Crop;
import com.kbf.management.service.CropService;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {


    @Autowired
    private CropService cropService;

    @Operation(summary = "Get all crops")
    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {
        return ResponseEntity.ok(cropService.getAllCrops());
    }

    @Operation(summary = "Get crop by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the crop"),
        @ApiResponse(responseCode = "404", description = "Crop not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCropById(@PathVariable Long id) {
        Crop crop = cropService.getCropById(id);
        return crop != null ? ResponseEntity.ok(crop) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new crop")
    @PostMapping
    public ResponseEntity<Crop> createCrop(@RequestBody Crop crop) {
        return ResponseEntity.ok(cropService.saveCrop(crop));
    }

    @Operation(summary = "Update crop by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Crop> updateCrop(@PathVariable Long id, @RequestBody Crop crop) {
        Crop updated = cropService.updateCrop(id, crop);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete crop by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropService.deleteCrop(id);
        return ResponseEntity.noContent().build();
    }
}

