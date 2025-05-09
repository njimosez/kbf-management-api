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

import com.kbf.management.model.Nutrient;
import com.kbf.management.service.NutrientService;

import java.util.List;

@Tag(name = "Nutrient", description = "Nutrient API")
@RestController
@RequestMapping("/api/nutrients")
public class NutrientController {

	@Autowired
	private NutrientService service;

	@Operation(summary = "Get all records")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Nutrient.class))),
			@ApiResponse(responseCode = "500", description = "Error") })
	@GetMapping
	public ResponseEntity<List<Nutrient>> getAll() {
		return ResponseEntity.ok(service.getNutrients());
	}

	@Operation(summary = "Get by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found", content = @Content(schema = @Schema(implementation = Nutrient.class))),
			@ApiResponse(responseCode = "404", description = "Not found") })
	@GetMapping("/{id}")
	public ResponseEntity<Nutrient> getById(@PathVariable Long id) {
		return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Create")
	@ApiResponse(responseCode = "200", description = "Created", content = @Content(schema = @Schema(implementation = Nutrient.class)))
	@PostMapping
	public ResponseEntity<Nutrient> create(@RequestBody Nutrient obj) {
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
