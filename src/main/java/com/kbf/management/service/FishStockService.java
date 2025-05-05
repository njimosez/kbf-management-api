/**
 * Test
 */
package com.kbf.management.service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.FishStockDto;
import com.kbf.management.model.FishStock;
import com.kbf.management.repository.FishStockRepository;
import com.kbf.management.repository.PondRepository;
import com.kbf.management.utils.Constants;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FishStockService {

	private final FishStockRepository fishStockRepo;
    private final PondRepository pondRepo;
    
    /**
     * Retrieve all fish stock records as DTOs.
     */
    public List<FishStockDto> getAll() {
        return fishStockRepo.findAll()
            .stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Retrieve a single fish stock by ID.
     */
    public FishStockDto getById(Long id) {
        FishStock stock = fishStockRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + id));
        return toDto(stock);
    }

    /**
     * Create a new fish stock entry.
     */
    @Transactional
    public FishStockDto create(FishStockDto dto) {
        FishStock stock = toEntity(dto);
        FishStock saved = fishStockRepo.save(stock);
        return toDto(saved);
    }

    /**
     * Update an existing fish stock record. 
     * function is here as reference only for future usage if applicable
     * Use transaction or fish sample instead to update fish stock
     */
    @Transactional
    public FishStockDto update(Long id, FishStockDto dto) {
        FishStock existing = fishStockRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + id));

        // Update fields
        existing.setInitialStock(dto.getInitialStock());
//        existing.setStockRemaining(dto.getStockRemaining());
//        existing.setQtySold(dto.getQtySold());
//        existing.setMortality(dto.getMortality());
//        existing.setReduction(dto.getReduction());
//        existing.setSoldOut(dto.getStockRemaining()<= Constants.ZERO ?true:false);
//        existing.setStage(dto.getStage()); // should be automatically updated based on duration with a progress bar .best to do on fron end
//        existing.setDateAdded(dto.getDateAdded() );//dto.getDate() != null ? dto.getDate() : LocalDate.now()
//        existing.setBatch(dto.getBatch());

        if (!existing.getPond().getPondId().equals(dto.getPondId())) {
            var pond = pondRepo.findById(dto.getPondId())
                .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + dto.getPondId()));
            existing.setPond(pond);
        }

        FishStock saved = fishStockRepo.save(existing);
        return toDto(saved);
    }

    /**
     * Delete a fish stock record.
     */
    @Transactional
    public void delete(Long id) {
        FishStock stock = fishStockRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + id));
        fishStockRepo.delete(stock);
    }

    /**
     * Convert DTO to entity (for creation).
     */
    private FishStock toEntity(FishStockDto dto) {
        FishStock stock = new FishStock();
        stock.setInitialStock(dto.getInitialStock());
        stock.setStockRemaining(dto.getInitialStock());
//        stock.setQtySold(dto.getQtySold());
//        stock.setMortality(dto.getMortality());
//        stock.setReduction(dto.getReduction());
//        stock.setSoldOut(dto.isSoldOut());
//        stock.setStage(dto.getStage());
        stock.setDateAdded(dto.getDateAdded());
        stock.setBatch(dto.getBatch());

        var pond = pondRepo.findById(dto.getPondId())
            .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + dto.getPondId()));
        stock.setPond(pond);
        return stock;
    }

    /**
     * Convert entity to DTO (for API responses).
     */
    private FishStockDto toDto(FishStock stock) {
        FishStockDto dto = new FishStockDto();
        dto.setInitialStock(stock.getInitialStock());
//        dto.setStockRemaining(stock.getStockRemaining());
//        dto.setQtySold(stock.getQtySold());
//        dto.setMortality(stock.getMortality());
//        dto.setReduction(stock.getReduction());
//        dto.setSoldOut(stock.isSoldOut());
//        dto.setStage(stock.getStage());
        dto.setDateAdded(stock.getDateAdded());
        dto.setBatch(stock.getBatch());
        dto.setPondId(stock.getPond().getPondId());
        return dto;
    }
}
