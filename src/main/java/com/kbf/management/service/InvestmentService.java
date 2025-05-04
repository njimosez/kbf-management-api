package com.kbf.management.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbf.management.dto.InvestmentDto;
import com.kbf.management.model.Investment;
import com.kbf.management.repository.InvestmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private final InvestmentRepository investmentRepo;

    /**
     * Fetch all investments.
     */
    public List<Investment> getAll() {
        return investmentRepo.findAll();
    }

    /**
     * Fetch a single investment by ID.
     */
    public Investment getById(Long id) {
        return investmentRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Investment not found: " + id));
    }

    /**
     * Create a new investment record from DTO.
     */
    @Transactional
    public Investment create(InvestmentDto dto) {
        Investment inv = mapDtoToEntity(new Investment(), dto);
        inv.setAccumulatedDepreciation(BigDecimal.ZERO);
        return investmentRepo.save(inv);
    }

    /**
     * Update an existing investment record.
     */
    @Transactional
    public Investment update(Long id, InvestmentDto dto) {
        Investment existing = getById(id);
        existing = mapDtoToEntity(existing, dto);
        return investmentRepo.save(existing);
    }

    /**
     * Delete an investment record.
     */
    @Transactional
    public void delete(Long id) {
        Investment inv = getById(id);
        investmentRepo.delete(inv);
    }

    /**
     * Apply depreciation as of a given date (straight-line only example).
     */
    @Transactional
    public void applyDepreciation(LocalDate asOfDate) {
        List<Investment> investments = investmentRepo.findAll();
        for (Investment inv : investments) {
            long yearsElapsed = asOfDate.getYear() - inv.getAcquisitionDate().getYear();
            BigDecimal cost = inv.getAcquisitionCost();
            BigDecimal salvage = inv.getSalvageValue();
            int life = inv.getUsefulLifeYears();

            if (yearsElapsed > 0 && inv.getDepreciationMethod() == Investment.DepreciationMethod.STRAIGHT_LINE) {
               
				BigDecimal annualDep = cost.subtract(salvage)
                    .divide(BigDecimal.valueOf(life),  RoundingMode.HALF_UP);
                BigDecimal accumulated = annualDep.multiply(BigDecimal.valueOf(Math.min(yearsElapsed, life)));
                inv.setAccumulatedDepreciation(accumulated);
                investmentRepo.save(inv);
            }
            // Extend for other methods...
        }
    }

    /**
     * Helper to map DTO to entity fields.
     */
    private Investment mapDtoToEntity(Investment inv, InvestmentDto dto) {
        inv.setName(dto.getName());
        inv.setAcquisitionCost(dto.getAcquisitionCost());
        inv.setAcquisitionDate(dto.getAcquisitionDate());
        inv.setUsefulLifeYears(dto.getUsefulLifeYears());
        inv.setSalvageValue(dto.getSalvageValue());
        inv.setDepreciationMethod(dto.getDepreciationMethod());
        return inv;
    }
}
