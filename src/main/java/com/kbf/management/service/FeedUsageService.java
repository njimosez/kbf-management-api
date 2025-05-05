/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.FeedUsageDto;
import com.kbf.management.model.FeedUsage;
import com.kbf.management.model.FishStock;
import com.kbf.management.model.Provender;
import com.kbf.management.repository.FeedUsageRepository;
import com.kbf.management.repository.FishStockRepository;
import com.kbf.management.repository.ProvenderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedUsageService {
	
	private final FeedUsageRepository feedUsageRepo;
    private final FishStockRepository fishStockRepo;
    private final ProvenderRepository provenderRepo;


    /**
     * Retrieve all feed usage records.
     */
    public List<FeedUsageDto> getAll() {
        return feedUsageRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Retrieve a single feed usage by ID.
     */
    public FeedUsageDto getById(Long id) {
        FeedUsage usage = feedUsageRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FeedUsage not found: " + id));
        return toDto(usage);
    }

    /**
     * Create a new feed usage record.
     */
    @Transactional
    public FeedUsageDto create(FeedUsageDto dto) {
        FishStock stock = fishStockRepo.findById(dto.getFishStockId())
            .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + dto.getFishStockId()));
        Provender prov = provenderRepo.findById(dto.getProvenderId())
            .orElseThrow(() -> new IllegalArgumentException("Provender not found: " + dto.getProvenderId()));

        FeedUsage usage = new FeedUsage();
        usage.setFeedType(dto.getFeedType());
        usage.setTimesFed(dto.getTimesFed());
        usage.setQuantityUsed(dto.getQuantityUsed());
        usage.setUsageDate(dto.getUsageDate());
        usage.setMortality(dto.getMortality());
        usage.setReduced(dto.getReduced());       
        usage.setAvgFishSize(dto.getAvgFishSize());
        usage.setFishStock(updateStock(stock,dto));       
        usage.setProvender(updateProvender(prov,dto));
        usage.setFishInPond(stock.getStockRemaining());
        

        FeedUsage saved = feedUsageRepo.save(usage);
        return toDto(saved);
    }
     
    /**
     * Reduce the quantity used from the provender stock
     * @param prov
     * @param dto
     * @return
     */
    private Provender updateProvender(Provender prov, FeedUsageDto dto) {
		prov.setQuantity(prov.getQuantity() - dto.getQuantityUsed());
		return prov;
	}

	/**
     * Fish stock record should be updated upon feeding which is done daily
     * @param stock
     * @param dto 
     * @return
     */
    private FishStock updateStock(FishStock stock, FeedUsageDto dto) {
     
      stock.setMortality(stock.getMortality() + dto.getMortality());
      stock.setReduction(stock.getReduction() + dto.getReduced());
      stock.setStockRemaining(stock.getStockRemaining() - (dto.getMortality() + dto.getReduced()));
     
     
		return stock;
	}

	/**
     * Update an existing feed usage record.
     */
    @Transactional
    public FeedUsageDto update(Long id, FeedUsageDto dto) {
        FeedUsage usage = feedUsageRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FeedUsage not found: " + id));
        FishStock stock = fishStockRepo.findById(dto.getFishStockId())
                .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + dto.getFishStockId()));
        Provender prov = provenderRepo.findById(dto.getProvenderId())
                .orElseThrow(() -> new IllegalArgumentException("Provender not found: " + dto.getProvenderId()));

        usage.setFeedType(dto.getFeedType());
        usage.setQuantityUsed(dto.getQuantityUsed());
        usage.setTimesFed(dto.getTimesFed());
        usage.setUsageDate(dto.getUsageDate());
        usage.setMortality(dto.getMortality());
        usage.setReduced(dto.getReduced());       
        usage.setAvgFishSize(dto.getAvgFishSize());
        usage.setFishStock(updateStock(stock,dto));
        prov.setQuantity(ProvQtyOnUpdate(prov,usage,dto));
        usage.setProvender(prov);
        usage.setFishInPond(stock.getStockRemaining());
       
        FeedUsage updated = feedUsageRepo.save(usage);
        return toDto(updated);
    }

    private double ProvQtyOnUpdate(Provender prov, FeedUsage usage, FeedUsageDto dto) {
    	double updatedQty = usage.getQuantityUsed() - dto.getQuantityUsed();
		return prov.getQuantity() - updatedQty;
	}

	/**
     * Delete a feed usage record.
     * TODO : revert provender usage and fish stock update
     */
    @Transactional
    public void delete(Long id) {
        FeedUsage usage = feedUsageRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FeedUsage not found: " + id));
        feedUsageRepo.delete(usage);
    }

    /**
     * Map entity to DTO for API responses.
     */
    private FeedUsageDto toDto(FeedUsage usage) {
        FeedUsageDto dto = new FeedUsageDto();
        dto.setId(usage.getFeedingId());
        dto.setFeedType(usage.getFeedType());
        dto.setQuantityUsed(usage.getQuantityUsed());
        dto.setUsageDate(usage.getUsageDate());
        dto.setMortality(usage.getMortality());
        dto.setReduced(usage.getReduced());
        dto.setFishStockId(usage.getFishStock().getFishStockId());
        dto.setProvenderId(usage.getProvender().getProvenderId());
        return dto;
    }
}

