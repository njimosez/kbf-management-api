package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.ProbioticDto;
import com.kbf.management.model.FishStock;
import com.kbf.management.model.Pond;
import com.kbf.management.model.Probiotic;
import com.kbf.management.model.Supplier;
import com.kbf.management.model.Transaction;
import com.kbf.management.model.WaterTreatment;
import com.kbf.management.repository.FishStockRepository;
import com.kbf.management.repository.PondRepository;
import com.kbf.management.repository.ProbioticRepository;
import com.kbf.management.repository.SupplierRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProbioticService {

	 private final ProbioticRepository repo;
	    private final PondRepository pondRepo;
	    private final FishStockRepository fishStockRepo;
	    private final SupplierRepository supplierRepo;

	    public List<ProbioticDto> getAll() {
	        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
	    }

	    public ProbioticDto getById(Long id) {
	        return toDto(repo.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("ProbioticApplication not found: " + id)));
	    }

	    @Transactional
	    public ProbioticDto create(ProbioticDto dto) {
	        Probiotic pa = new Probiotic();
	        if (dto.getPondId() != null) {
	            Pond pond = pondRepo.findById(dto.getPondId())
	                .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + dto.getPondId()));
	            pa.setPond(pond);
	        }
	        if (dto.getFishStockId() != null) {
	            FishStock fs = fishStockRepo.findById(dto.getFishStockId())
	                .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + dto.getFishStockId()));
	            pa.setFishStock(fs);
	        }
	        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
	            .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + dto.getSupplierId()));
	        pa.setSupplier(supplier);

	        pa.setApplicationDate(dto.getApplicationDate());
	        pa.setProbioticName(dto.getProbioticName());
	        pa.setPurpose(dto.getPurpose());
	        pa.setMethod(dto.getMethod());
	        pa.setQuantity(dto.getQuantity());
	        pa.setUnit(dto.getUnit());
	        pa.setConcentration(dto.getConcentration());
	        pa.setManufactureDate(dto.getManufactureDate());
	        pa.setExpiryDate(dto.getExpiryDate());
	        pa.setUsageInstructions(dto.getUsageInstructions());
	        pa.setRemarks(dto.getRemarks());
	        Probiotic saved = repo.save(pa);
	        return toDto(saved);
	    }

	    @Transactional
	    public ProbioticDto update(Long id, ProbioticDto dto) {
	        Probiotic pa = repo.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("ProbioticApplication not found: " + id));
	        if (dto.getPondId() != null) {
	            Pond pond = pondRepo.findById(dto.getPondId())
	                .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + dto.getPondId()));
	            pa.setPond(pond);
	        } else {
	            pa.setPond(null);
	        }
	        if (dto.getFishStockId() != null) {
	            FishStock fs = fishStockRepo.findById(dto.getFishStockId())
	                .orElseThrow(() -> new IllegalArgumentException("FishStock not found: " + dto.getFishStockId()));
	            pa.setFishStock(fs);
	        } else {
	            pa.setFishStock(null);
	        }
	        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
	            .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + dto.getSupplierId()));
	        pa.setSupplier(supplier);

	        pa.setApplicationDate(dto.getApplicationDate());
	        pa.setProbioticName(dto.getProbioticName());
	        pa.setPurpose(dto.getPurpose());
	        pa.setMethod(dto.getMethod());
	        pa.setQuantity(dto.getQuantity());
	        pa.setUnit(dto.getUnit());
	        pa.setConcentration(dto.getConcentration());
	        pa.setManufactureDate(dto.getManufactureDate());
	        pa.setExpiryDate(dto.getExpiryDate());
	        pa.setUsageInstructions(dto.getUsageInstructions());
	        pa.setRemarks(dto.getRemarks());
	        Probiotic updated = repo.save(pa);
	        return toDto(updated);
	    }

	    @Transactional
	    public void delete(Long id) {
	        repo.delete(repo.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("ProbioticApplication not found: " + id)));
	    }

	    private ProbioticDto toDto(Probiotic pa) {
	        ProbioticDto dto = new ProbioticDto();
	        dto.setId(pa.getProbioticId());
	        dto.setPondId(pa.getPond() != null ? pa.getPond().getPondId() : null);
	        dto.setFishStockId(pa.getFishStock() != null ? pa.getFishStock().getFishStockId() : null);
	        dto.setSupplierId(pa.getSupplier().getSupplierId());
	        dto.setApplicationDate(pa.getApplicationDate());
	        dto.setProbioticName(pa.getProbioticName());
	        dto.setPurpose(pa.getPurpose());
	        dto.setMethod(pa.getMethod());
	        dto.setQuantity(pa.getQuantity());
	        dto.setUnit(pa.getUnit());
	        dto.setConcentration(pa.getConcentration());
	        dto.setManufactureDate(pa.getManufactureDate());
	        dto.setExpiryDate(pa.getExpiryDate());
	        dto.setUsageInstructions(pa.getUsageInstructions());
	        dto.setRemarks(pa.getRemarks());
	        dto.setWaterTreatmentIds(pa.getWaterTreatments().stream()
	            .map(WaterTreatment::getTreatmentId).collect(Collectors.toList()));
	        dto.setTransactionIds(pa.getTransactions().stream()
	            .map(Transaction::getTransactionId).collect(Collectors.toList()));
	        return dto;
	    }
	}
