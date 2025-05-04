/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.CustomerDto;
import com.kbf.management.dto.SupplierDto;
import com.kbf.management.model.Customer;
import com.kbf.management.model.Supplier;
import com.kbf.management.model.Transaction;
import com.kbf.management.repository.CustomerRepository;
import com.kbf.management.repository.SupplierRepository;
import com.kbf.management.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepo;
    private final TransactionRepository transactionRepo;

    public List<CustomerDto> getAll() {
        return customerRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public CustomerDto getById(Long id) {
        Customer c = customerRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
        return toDto(c);
    }

    @Transactional
    public CustomerDto create(CustomerDto dto) {
        Customer c = new Customer();
        c.setName(dto.getName());
        c.setContact(dto.getContact());
        c.setAddress(dto.getAddress());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        if (dto.getTransactionIds() != null) {
            List<Transaction> txs = dto.getTransactionIds().stream()
                .map(txId -> transactionRepo.findById(txId)
                    .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + txId)))
                .collect(Collectors.toList());
            c.setTransactions(txs);
        }
        Customer saved = customerRepo.save(c);
        return toDto(saved);
    }

    @Transactional
    public CustomerDto update(Long id, CustomerDto dto) {
        Customer c = customerRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
        c.setName(dto.getName());
        c.setContact(dto.getContact());
        c.setAddress(dto.getAddress());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        if (dto.getTransactionIds() != null) {
            List<Transaction> txs = dto.getTransactionIds().stream()
                .map(txId -> transactionRepo.findById(txId)
                    .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + txId)))
                .collect(Collectors.toList());
            c.setTransactions(txs);
        } else {
            c.setTransactions(null);
        }
        Customer updated = customerRepo.save(c);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        Customer c = customerRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
        customerRepo.delete(c);
    }

    private CustomerDto toDto(Customer c) {
        CustomerDto dto = new CustomerDto();
        dto.setId(c.getCustomerId());
        dto.setName(c.getName());
        dto.setContact(c.getContact());
        dto.setAddress(c.getAddress());
        dto.setEmail(c.getEmail());
        dto.setPhone(c.getPhone());
        dto.setTransactionIds(
            c.getTransactions().stream().map(Transaction::getTransactionId).collect(Collectors.toList())
        );
        return dto;
    }
}