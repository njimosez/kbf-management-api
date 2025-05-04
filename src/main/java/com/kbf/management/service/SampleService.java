/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.SampleDto;
import com.kbf.management.model.FishStock;
import com.kbf.management.model.Sample;
import com.kbf.management.repository.FishStockRepository;
import com.kbf.management.repository.SampleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleService {

	private final SampleRepository samplingRepo;
    private final FishStockRepository fishStockRepo;

    public List<SampleDto> getAll() {
        return samplingRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public SampleDto getById(Long id) {
        Sample sampling = samplingRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sample not found: " + id));
        return toDto(sampling);
    }

    @Transactional
    public SampleDto create(SampleDto dto) {
        FishStock fishStock = fishStockRepo.findById(dto.getFishStockId())
            .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + dto.getFishStockId()));

        Sample sampling = new Sample();
        sampling.setSpecy(dto.getSpecy());
        sampling.setSampleDate(dto.getSampleDate());
        sampling.setSampleQty(dto.getSampleQty());
        sampling.setTotalSampleWeight(dto.getTotalSampleWeight());
        sampling.setAvgFishSize(Math.round(dto.getTotalSampleWeight()/dto.getSampleQty()));
        sampling.setTargetSize(dto.getTargetSize());
        sampling.setFishStock(fishStock);

        Sample saved = samplingRepo.save(sampling);
        return toDto(saved);
    }

    @Transactional
    public SampleDto update(Long id, SampleDto dto) {
        Sample sampling = samplingRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sample not found: " + id));

        sampling.setSpecy(dto.getSpecy());
        sampling.setSampleDate(dto.getSampleDate());
        sampling.setSampleQty(dto.getSampleQty());
        sampling.setTotalSampleWeight(dto.getTotalSampleWeight());
        sampling.setAvgFishSize(Math.round(dto.getTotalSampleWeight()/dto.getSampleQty()));
        sampling.setTargetSize(dto.getTargetSize());
        // fishStock change is not allowed in update

        Sample updated = samplingRepo.save(sampling);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        Sample sampling = samplingRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sample not found: " + id));
        samplingRepo.delete(sampling);
    }

    private SampleDto toDto(Sample sampling) {
        SampleDto dto = new SampleDto();
        dto.setId(sampling.getSampleId());
        dto.setSpecy(sampling.getSpecy());
        dto.setSampleDate(sampling.getSampleDate());
        dto.setSampleQty(sampling.getSampleQty());
        dto.setTotalSampleWeight(sampling.getTotalSampleWeight());
        dto.setAvgFishSize(sampling.getAvgFishSize());
        dto.setTargetSize(sampling.getTargetSize());
        dto.setFishStockId(sampling.getFishStock().getFishStockId());
        return dto;
    }
}