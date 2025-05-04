package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.dto.WaterAnalysisDto;
import com.kbf.management.model.Pond;
import com.kbf.management.model.WaterAnalysis;
import com.kbf.management.repository.PondRepository;
import com.kbf.management.repository.WaterAnalysisRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WaterAnalysisService {

	private final WaterAnalysisRepository analysisRepo;
    private final PondRepository pondRepo;

    public List<WaterAnalysisDto> getAll() {
        return analysisRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public WaterAnalysisDto getById(Long id) {
        WaterAnalysis wa = analysisRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("WaterAnalysis not found: " + id));
        return toDto(wa);
    }

    @Transactional
    public WaterAnalysisDto create(WaterAnalysisDto dto) {
        Pond pond = pondRepo.findById(dto.getPondId())
            .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + dto.getPondId()));

        WaterAnalysis wa = new WaterAnalysis();
        wa.setPond(pond);
        wa.setPh(dto.getPh());
        wa.setAmmonia(dto.getAmmonia());
        wa.setNitrite(dto.getNitrite());
        wa.setNitrate(dto.getNitrate());
        wa.setOxygen(dto.getOxygen());
        wa.setTemperature(dto.getTemperature());
        wa.setHardness(dto.getHardness());
        wa.setAlkalinity(dto.getAlkalinity());
        wa.setNotes(dto.getNotes());
        wa.setAnalysisDate(dto.getAnalysisDate());

        WaterAnalysis saved = analysisRepo.save(wa);
        return toDto(saved);
    }

    @Transactional
    public WaterAnalysisDto update(Long id, WaterAnalysisDto dto) {
        WaterAnalysis wa = analysisRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("WaterAnalysis not found: " + id));

        wa.setPh(dto.getPh());
        wa.setAmmonia(dto.getAmmonia());
        wa.setNitrite(dto.getNitrite());
        wa.setNitrate(dto.getNitrate());
        wa.setOxygen(dto.getOxygen());
        wa.setTemperature(dto.getTemperature());
        wa.setHardness(dto.getHardness());
        wa.setAlkalinity(dto.getAlkalinity());
        wa.setNotes(dto.getNotes());
        wa.setAnalysisDate(dto.getAnalysisDate());
        // pond association remains unchanged

        WaterAnalysis updated = analysisRepo.save(wa);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        WaterAnalysis wa = analysisRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("WaterAnalysis not found: " + id));
        analysisRepo.delete(wa);
    }

    private WaterAnalysisDto toDto(WaterAnalysis wa) {
        WaterAnalysisDto dto = new WaterAnalysisDto();
        dto.setId(wa.getAnalysisId());
        dto.setPh(wa.getPh());
        dto.setAmmonia(wa.getAmmonia());
        dto.setNitrite(wa.getNitrite());
        dto.setNitrate(wa.getNitrate());
        dto.setOxygen(wa.getOxygen());
        dto.setTemperature(wa.getTemperature());
        dto.setHardness(wa.getHardness());
        dto.setAlkalinity(wa.getAlkalinity());
        dto.setNotes(wa.getNotes());
        dto.setAnalysisDate(wa.getAnalysisDate());
        dto.setPondId(wa.getPond().getPondId());
        return dto;
    }
}