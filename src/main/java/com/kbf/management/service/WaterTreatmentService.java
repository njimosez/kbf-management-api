package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.WaterTreatmentDto;
import com.kbf.management.model.Pond;
import com.kbf.management.model.Probiotic;
import com.kbf.management.model.WaterTreatment;
import com.kbf.management.repository.PondRepository;
import com.kbf.management.repository.ProbioticRepository;
import com.kbf.management.repository.WaterTreatmentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaterTreatmentService {

    private final WaterTreatmentRepository treatmentRepo;
    private final PondRepository pondRepo;
    private final ProbioticRepository probioticRepo;

    public List<WaterTreatmentDto> getAll() {
        return treatmentRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public WaterTreatmentDto getById(Long id) {
        WaterTreatment wt = treatmentRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("WaterTreatment not found: " + id));
        return toDto(wt);
    }

    @Transactional
    public WaterTreatmentDto create(WaterTreatmentDto dto) {
        Pond pond = pondRepo.findById(dto.getPondId())
            .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + dto.getPondId()));

        WaterTreatment wt = new WaterTreatment();
        wt.setPond(pond);
        wt.setTreatmentType(dto.getTreatmentType());
        wt.setChemicalUsed(dto.getChemicalUsed());
        if (dto.getProbioticApplicationId() != null) {
            Probiotic pa = probioticRepo.findById(dto.getProbioticApplicationId())
                .orElseThrow(() -> new IllegalArgumentException("ProbioticApplication not found: " + dto.getProbioticApplicationId()));
            wt.setProbiotic(pa);
        }
        wt.setTreatmentDate(dto.getTreatmentDate());
        wt.setEffectiveness(dto.getEffectiveness());
        wt.setDosage(dto.getDosage());
        wt.setDosageUnit(dto.getDosageUnit());
        wt.setAppliedBy(dto.getAppliedBy());

        WaterTreatment saved = treatmentRepo.save(wt);
        return toDto(saved);
    }

    @Transactional
    public WaterTreatmentDto update(Long id, WaterTreatmentDto dto) {
        WaterTreatment wt = treatmentRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("WaterTreatment not found: " + id));

        wt.setTreatmentType(dto.getTreatmentType());
        wt.setChemicalUsed(dto.getChemicalUsed());
        if (dto.getProbioticApplicationId() != null) {
            Probiotic pa = probioticRepo.findById(dto.getProbioticApplicationId())
                .orElseThrow(() -> new IllegalArgumentException("ProbioticApplication not found: " + dto.getProbioticApplicationId()));
            wt.setProbiotic(pa);
        } else {
            wt.setProbiotic(null);
        }
        wt.setTreatmentDate(dto.getTreatmentDate());
        wt.setEffectiveness(dto.getEffectiveness());
        wt.setDosage(dto.getDosage());
        wt.setDosageUnit(dto.getDosageUnit());
        wt.setAppliedBy(dto.getAppliedBy());

        WaterTreatment updated = treatmentRepo.save(wt);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        WaterTreatment wt = treatmentRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("WaterTreatment not found: " + id));
        treatmentRepo.delete(wt);
    }

    private WaterTreatmentDto toDto(WaterTreatment wt) {
        WaterTreatmentDto dto = new WaterTreatmentDto();
        dto.setId(wt.getTreatmentId());
        dto.setTreatmentType(wt.getTreatmentType());
        dto.setChemicalUsed(wt.getChemicalUsed());
        dto.setProbioticApplicationId(wt.getProbiotic() != null ? wt.getProbiotic().getProbioticId() : null);
        dto.setTreatmentDate(wt.getTreatmentDate());
        dto.setEffectiveness(wt.getEffectiveness());
        dto.setDosage(wt.getDosage());
        dto.setDosageUnit(wt.getDosageUnit());
        dto.setAppliedBy(wt.getAppliedBy());
        dto.setPondId(wt.getPond().getPondId());
        return dto;
    }
}