package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.FarmEquipmentDto;
import com.kbf.management.model.FarmEquipment;
import com.kbf.management.model.MaintenanceRecord;
import com.kbf.management.model.Transaction;
import com.kbf.management.repository.FarmEquipmentRepository;
import com.kbf.management.repository.MaintenanceRecordRepository;
import com.kbf.management.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmEquipmentService {

    private final FarmEquipmentRepository equipmentRepo;
    private final TransactionRepository transactionRepo;
    private final MaintenanceRecordRepository maintenanceRepo;

    public List<FarmEquipmentDto> getAll() {
        return equipmentRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public FarmEquipmentDto getById(Long id) {
        FarmEquipment eq = equipmentRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FarmEquipment not found: " + id));
        return toDto(eq);
    }

    @Transactional
    public FarmEquipmentDto create(FarmEquipmentDto dto) {
        FarmEquipment eq = new FarmEquipment();
        eq.setName(dto.getName());
        eq.setEquipmentType(dto.getEquipmentType());
        eq.setManufacturer(dto.getManufacturer());
        eq.setQty(dto.getQty());
        eq.setPurchaseDate(dto.getPurchaseDate());
        eq.setCost(dto.getCost());
        eq.setMaintenanceSchedule(dto.getMaintenanceSchedule());
        eq.setLocation(dto.getLocation());
        eq.setStatus(dto.getStatus());
        if (dto.getPurchaseTransactionId() != null) {
            Transaction tx = transactionRepo.findById(dto.getPurchaseTransactionId())
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + dto.getPurchaseTransactionId()));
            eq.setPurchaseTransaction(tx);
        }
        if (dto.getMaintenanceRecordIds() != null) {
            List<MaintenanceRecord> recs = dto.getMaintenanceRecordIds().stream()
                .map(rid -> maintenanceRepo.findById(rid)
                    .orElseThrow(() -> new IllegalArgumentException("MaintenanceRecord not found: " + rid)))
                .collect(Collectors.toList());
            eq.setMaintenanceRecords(recs);
        }
        FarmEquipment saved = equipmentRepo.save(eq);
        return toDto(saved);
    }

    @Transactional
    public FarmEquipmentDto update(Long id, FarmEquipmentDto dto) {
        FarmEquipment eq = equipmentRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FarmEquipment not found: " + id));
        eq.setName(dto.getName());
        eq.setEquipmentType(dto.getEquipmentType());
        eq.setManufacturer(dto.getManufacturer());
        eq.setQty(dto.getQty());
        eq.setPurchaseDate(dto.getPurchaseDate());
        eq.setCost(dto.getCost());
        eq.setMaintenanceSchedule(dto.getMaintenanceSchedule());
        eq.setLocation(dto.getLocation());
        eq.setStatus(dto.getStatus());
        if (dto.getPurchaseTransactionId() != null) {
            Transaction tx = transactionRepo.findById(dto.getPurchaseTransactionId())
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + dto.getPurchaseTransactionId()));
            eq.setPurchaseTransaction(tx);
        } else {
            eq.setPurchaseTransaction(null);
        }
        if (dto.getMaintenanceRecordIds() != null) {
            List<MaintenanceRecord> recs = dto.getMaintenanceRecordIds().stream()
                .map(rid -> maintenanceRepo.findById(rid)
                    .orElseThrow(() -> new IllegalArgumentException("MaintenanceRecord not found: " + rid)))
                .collect(Collectors.toList());
            eq.setMaintenanceRecords(recs);
        } else {
            eq.setMaintenanceRecords(null);
        }
        FarmEquipment updated = equipmentRepo.save(eq);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        FarmEquipment eq = equipmentRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FarmEquipment not found: " + id));
        equipmentRepo.delete(eq);
    }

    private FarmEquipmentDto toDto(FarmEquipment eq) {
        FarmEquipmentDto dto = new FarmEquipmentDto();
        dto.setId(eq.getEquipmentId());
        dto.setName(eq.getName());
        dto.setEquipmentType(eq.getEquipmentType());
        dto.setManufacturer(eq.getManufacturer());
        dto.setQty(eq.getQty());
        dto.setPurchaseDate(eq.getPurchaseDate());
        dto.setCost(eq.getCost());
        dto.setMaintenanceSchedule(eq.getMaintenanceSchedule());
        dto.setLocation(eq.getLocation());
        dto.setStatus(eq.getStatus());
        dto.setPurchaseTransactionId(eq.getPurchaseTransaction()!=null?eq.getPurchaseTransaction().getTransactionId():null);
        dto.setMaintenanceRecordIds(eq.getMaintenanceRecords()!=null?
            eq.getMaintenanceRecords().stream().map(MaintenanceRecord::getMaintenanceId).collect(Collectors.toList()):null);
        return dto;
    }
}
