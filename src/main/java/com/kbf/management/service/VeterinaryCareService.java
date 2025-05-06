package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.VeterinaryCareDto;
import com.kbf.management.model.FishStock;
import com.kbf.management.model.Transaction;
import com.kbf.management.model.VeterinaryCare;
import com.kbf.management.repository.FishStockRepository;
import com.kbf.management.repository.TransactionRepository;
import com.kbf.management.repository.VeterinaryCareRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinaryCareService {

    private final VeterinaryCareRepository repo;
    private final FishStockRepository fishStockRepo;
    private final TransactionRepository transactionRepo;

    public List<VeterinaryCareDto> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public VeterinaryCareDto getById(Long id) {
        VeterinaryCare vc = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("VeterinaryCare not found: " + id));
        return toDto(vc);
    }

    @Transactional
    public VeterinaryCareDto create(VeterinaryCareDto dto) {
        VeterinaryCare vc = new VeterinaryCare();
        FishStock fs = fishStockRepo.findById(dto.getFishStockId())
            .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + dto.getFishStockId()));
        vc.setFishStock(fs);
        vc.setCareDate(dto.getCareDate());
        vc.setDosage(dto.getDosage());
        vc.setDosageUnit(dto.getDosageUnit());
        vc.setAnimalType(dto.getAnimalType());
        vc.setSpeciesOrBreed(dto.getSpeciesOrBreed());
        vc.setMedication(dto.getMedication());
        vc.setDiagnosis(dto.getDiagnosis());
        vc.setTreatment(dto.getTreatment());
        vc.setVeterinarianName(dto.getVeterinarianName());
        vc.setNotes(dto.getNotes());
        if (dto.getTransactionId() != null) {
            Transaction tx = transactionRepo.findById(dto.getTransactionId())
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + dto.getTransactionId()));
            vc.setTransaction(tx);
        }
        VeterinaryCare saved = repo.save(vc);
        return toDto(saved);
    }

    @Transactional
    public VeterinaryCareDto update(Long id, VeterinaryCareDto dto) {
        VeterinaryCare vc = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("VeterinaryCare not found: " + id));
        FishStock fs = fishStockRepo.findById(dto.getFishStockId())
            .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + dto.getFishStockId()));
        vc.setFishStock(fs);
        vc.setCareDate(dto.getCareDate());
        vc.setDosage(dto.getDosage());
        vc.setDosageUnit(dto.getDosageUnit());
        vc.setAnimalType(dto.getAnimalType());
        vc.setSpeciesOrBreed(dto.getSpeciesOrBreed());
        vc.setMedication(dto.getMedication());
        vc.setDiagnosis(dto.getDiagnosis());
        vc.setTreatment(dto.getTreatment());
        vc.setVeterinarianName(dto.getVeterinarianName());
        vc.setNotes(dto.getNotes());
        if (dto.getTransactionId() != null) {
            Transaction tx = transactionRepo.findById(dto.getTransactionId())
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + dto.getTransactionId()));
            vc.setTransaction(tx);
        } else {
            vc.setTransaction(null);
        }
        VeterinaryCare updated = repo.save(vc);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        VeterinaryCare vc = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("VeterinaryCare not found: " + id));
        repo.delete(vc);
    }

    private VeterinaryCareDto toDto(VeterinaryCare vc) {
        VeterinaryCareDto dto = new VeterinaryCareDto();
        dto.setId(vc.getVetId());
        dto.setFishStockId(vc.getFishStock().getFishStockId());
        dto.setCareDate(vc.getCareDate());
        dto.setDosage(vc.getDosage());
        dto.setDosageUnit(vc.getDosageUnit());
        dto.setAnimalType(vc.getAnimalType());
        dto.setSpeciesOrBreed(vc.getSpeciesOrBreed());
        dto.setMedication(vc.getMedication());
        dto.setDiagnosis(vc.getDiagnosis());
        dto.setTreatment(vc.getTreatment());
        dto.setVeterinarianName(vc.getVeterinarianName());
        dto.setNotes(vc.getNotes());
        dto.setTransactionId(vc.getTransaction() != null ? vc.getTransaction().getTransactionId() : null);
        return dto;
    }
}
