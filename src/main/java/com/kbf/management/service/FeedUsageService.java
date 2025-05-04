/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
        usage.setQuantityUsed(dto.getQuantityUsed());
        usage.setUsageDate(dto.getUsageDate());
        usage.setMortality(dto.getMortality());
        usage.setReduced(dto.getReduced());
        usage.setFishStock(stock);
        usage.setProvender(prov);

        FeedUsage saved = feedUsageRepo.save(usage);
        return toDto(saved);
    }

    /**
     * Update an existing feed usage record.
     */
    @Transactional
    public FeedUsageDto update(Long id, FeedUsageDto dto) {
        FeedUsage usage = feedUsageRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FeedUsage not found: " + id));

        usage.setFeedType(dto.getFeedType());
        usage.setQuantityUsed(dto.getQuantityUsed());
        usage.setUsageDate(dto.getUsageDate());
        usage.setMortality(dto.getMortality());
        usage.setReduced(dto.getReduced());
        // associations not changed to preserve integrity

        FeedUsage updated = feedUsageRepo.save(usage);
        return toDto(updated);
    }

    /**
     * Delete a feed usage record.
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

