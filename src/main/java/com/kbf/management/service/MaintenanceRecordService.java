package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.MaintenanceRecordDto;
import com.kbf.management.model.FarmEquipment;
import com.kbf.management.model.MaintenanceRecord;
import com.kbf.management.repository.FarmEquipmentRepository;
import com.kbf.management.repository.MaintenanceRecordRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaintenanceRecordService {

    private final MaintenanceRecordRepository repo;
    private final FarmEquipmentRepository equipmentRepo;

    public List<MaintenanceRecordDto> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public MaintenanceRecordDto getById(Long id) {
        MaintenanceRecord mr = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("MaintenanceRecord not found: " + id));
        return toDto(mr);
    }

    @Transactional
    public MaintenanceRecordDto create(MaintenanceRecordDto dto) {
        MaintenanceRecord mr = new MaintenanceRecord();
        FarmEquipment eq = equipmentRepo.findById(dto.getEquipmentId())
            .orElseThrow(() -> new IllegalArgumentException("Equipment not found: " + dto.getEquipmentId()));
        mr.setFarmEquipment(eq);
        mr.setMaintenanceDate(dto.getMaintenanceDate());
        mr.setDescription(dto.getDescription());
        mr.setCost(dto.getCost());
        mr.setPerformedBy(dto.getPerformedBy());
        mr.setNotes(dto.getNotes());
        MaintenanceRecord saved = repo.save(mr);
        return toDto(saved);
    }

    @Transactional
    public MaintenanceRecordDto update(Long id, MaintenanceRecordDto dto) {
        MaintenanceRecord mr = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("MaintenanceRecord not found: " + id));
        FarmEquipment eq = equipmentRepo.findById(dto.getEquipmentId())
            .orElseThrow(() -> new IllegalArgumentException("Equipment not found: " + dto.getEquipmentId()));
        mr.setFarmEquipment(eq);
        mr.setMaintenanceDate(dto.getMaintenanceDate());
        mr.setDescription(dto.getDescription());
        mr.setCost(dto.getCost());
        mr.setPerformedBy(dto.getPerformedBy());
        mr.setNotes(dto.getNotes());
        MaintenanceRecord updated = repo.save(mr);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        MaintenanceRecord mr = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("MaintenanceRecord not found: " + id));
        repo.delete(mr);
    }

    private MaintenanceRecordDto toDto(MaintenanceRecord mr) {
        MaintenanceRecordDto dto = new MaintenanceRecordDto();
        dto.setId(mr.getMaintenanceId());
        dto.setEquipmentId(mr.getFarmEquipment().getEquipmentId());
        dto.setMaintenanceDate(mr.getMaintenanceDate());
        dto.setDescription(mr.getDescription());
        dto.setCost(mr.getCost());
        dto.setPerformedBy(mr.getPerformedBy());
        dto.setNotes(mr.getNotes());
        return dto;
    }
}