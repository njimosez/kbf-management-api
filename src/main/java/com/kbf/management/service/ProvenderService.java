package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.dto.ProvenderDto;
import com.kbf.management.model.FeedComposition;
import com.kbf.management.model.Provender;
import com.kbf.management.model.Supplier;
import com.kbf.management.repository.ProvenderRepository;
import com.kbf.management.repository.SupplierRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvenderService {

	private final ProvenderRepository provenderRepo;
    private final SupplierRepository supplierRepo;

    /**
     * Retrieve all provender batches.
     */
    public List<ProvenderDto> getAll() {
        return provenderRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Retrieve a single provender record by ID.
     */
    public ProvenderDto getById(Long id) {
        Provender p = provenderRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Provender not found: " + id));
        return toDto(p);
    }

    /**
     * Create a new provender batch.
     */
    @Transactional
    public ProvenderDto create(ProvenderDto dto) {
        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
            .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + dto.getSupplierId()));

        Provender p = new Provender();
        p.setFeedType(dto.getFeedType());
        p.setQuantity(dto.getQuantity());
        p.setLastRestocked(dto.getLastRestocked());
        p.setPricePerKg(dto.getPricePerKg());
        p.setExpiryDate(dto.getExpiryDate());
        p.setFeedingNotes(dto.getFeedingNotes());
        p.setManufacturer(dto.getManufacturer());
        p.setSupplier(supplier);

        // map compositions
        if (dto.getFeedCompositions() != null) {
            List<FeedComposition> comps = dto.getFeedCompositions().stream().map(c -> {
                FeedComposition fc = new FeedComposition();
               // fc.setIngredient(c.getIngredientId())
                fc.setPercentage(c.getPercentage());
                fc.setProtein(c.getProtein());
                fc.setFat(c.getFat());
                fc.setFiber(c.getFiber());
                fc.setEnergy(c.getEnergy());
                fc.setProvender(p);
                return fc;
            }).collect(Collectors.toList());
            p.setFeedCompositions(comps);
        }

        Provender saved = provenderRepo.save(p);
        return toDto(saved);
    }

    /**
     * Update an existing provender batch.
     */
    @Transactional
    public ProvenderDto update(Long id, ProvenderDto dto) {
        Provender p = provenderRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Provender not found: " + id));
        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
            .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + dto.getSupplierId()));

        p.setFeedType(dto.getFeedType());
        p.setQuantity(dto.getQuantity());
        p.setLastRestocked(dto.getLastRestocked());
        p.setPricePerKg(dto.getPricePerKg());
        p.setExpiryDate(dto.getExpiryDate());
        p.setFeedingNotes(dto.getFeedingNotes());
        p.setManufacturer(dto.getManufacturer());
        p.setSupplier(supplier);
        // handle feedCompositions update if needed

        Provender updated = provenderRepo.save(p);
        return toDto(updated);
    }

    /**
     * Delete a provender batch by ID.
     */
    @Transactional
    public void delete(Long id) {
        Provender p = provenderRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Provender not found: " + id));
        provenderRepo.delete(p);
    }

    /**
     * Map entity to DTO for API responses.
     */
    private ProvenderDto toDto(Provender p) {
        ProvenderDto dto = new ProvenderDto();
        dto.setFeedType(p.getFeedType());
        dto.setQuantity(p.getQuantity());
        dto.setLastRestocked(p.getLastRestocked());
        dto.setPricePerKg(p.getPricePerKg());
        dto.setExpiryDate(p.getExpiryDate());
        dto.setFeedingNotes(p.getFeedingNotes());
        dto.setManufacturer(p.getManufacturer());
        dto.setSupplierId(p.getSupplier().getSupplierId());
//        if (p.getFeedCompositions() != null) {
//            dto.setFeedCompositions(
//                p.getFeedCompositions().stream().map(c -> {
 //                   FeedCompositionDto cd = new FeedCompositionDto();
//                    cd.setIngredient(c.getIngredient());
//                    cd.setPercentage(c.getPercentage());
//                    cd.setProtein(c.getProtein());
//                    cd.setFat(c.getFat());
//                    cd.setFiber(c.getFiber());
//                    cd.setEnergy(c.getEnergy());
//                    return cd;
 //               }).collect(Collectors.toList()));
//        }
        return dto;
    }
}
