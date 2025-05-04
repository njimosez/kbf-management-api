package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.PayrollDto;
import com.kbf.management.model.Payroll;
import com.kbf.management.model.Personnel;
import com.kbf.management.model.Transaction;
import com.kbf.management.repository.PayrollRepository;
import com.kbf.management.repository.PersonnelRepository;
import com.kbf.management.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollRepository payrollRepo;
    private final PersonnelRepository personnelRepo;
    private final TransactionRepository transactionRepo;

    public List<PayrollDto> getAll() {
        return payrollRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public PayrollDto getById(Long id) {
        Payroll p = payrollRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Payroll not found: " + id));
        return toDto(p);
    }

    @Transactional
    public PayrollDto create(PayrollDto dto) {
        Payroll p = new Payroll();
        Personnel per = personnelRepo.findById(dto.getPersonnelId())
            .orElseThrow(() -> new IllegalArgumentException("Personnel not found: " + dto.getPersonnelId()));
        p.setPersonnel(per);
        p.setBaseSalary(dto.getBaseSalary());
        p.setBonus(dto.getBonus());
        p.setDeduction(dto.getDeduction());
        p.setNetPay(dto.getNetPay());
        p.setPaymentDate(dto.getPaymentDate());
        p.setPaymentMethod(dto.getPaymentMethod());
        if (dto.getTransactionIds() != null) {
            List<Transaction> txs = dto.getTransactionIds().stream()
                .map(txId -> transactionRepo.findById(txId)
                    .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + txId)))
                .collect(Collectors.toList());
            p.setTransactions(txs);
        }
        Payroll saved = payrollRepo.save(p);
        return toDto(saved);
    }

    @Transactional
    public PayrollDto update(Long id, PayrollDto dto) {
        Payroll p = payrollRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Payroll not found: " + id));
        Personnel per = personnelRepo.findById(dto.getPersonnelId())
            .orElseThrow(() -> new IllegalArgumentException("Personnel not found: " + dto.getPersonnelId()));
        p.setPersonnel(per);
        p.setBaseSalary(dto.getBaseSalary());
        p.setBonus(dto.getBonus());
        p.setDeduction(dto.getDeduction());
        p.setNetPay(dto.getNetPay());
        p.setPaymentDate(dto.getPaymentDate());
        p.setPaymentMethod(dto.getPaymentMethod());
        if (dto.getTransactionIds() != null) {
            List<Transaction> txs = dto.getTransactionIds().stream()
                .map(txId -> transactionRepo.findById(txId)
                    .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + txId)))
                .collect(Collectors.toList());
            p.setTransactions(txs);
        } else {
            p.setTransactions(null);
        }
        Payroll updated = payrollRepo.save(p);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        Payroll p = payrollRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Payroll not found: " + id));
        payrollRepo.delete(p);
    }

    private PayrollDto toDto(Payroll p) {
        PayrollDto dto = new PayrollDto();
        dto.setId(p.getPayId());
        dto.setPersonnelId(p.getPersonnel().getPersonnelId());
        dto.setBaseSalary(p.getBaseSalary());
        dto.setBonus(p.getBonus());
        dto.setDeduction(p.getDeduction());
        dto.setNetPay(p.getNetPay());
        dto.setPaymentDate(p.getPaymentDate());
        dto.setPaymentMethod(p.getPaymentMethod());
        dto.setTransactionIds(
            p.getTransactions()
                .stream()
                .map(Transaction::getTransactionId)
                .collect(Collectors.toList())
        );
        return dto;
    }
}
