/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.SupplierDto;
import com.kbf.management.model.Supplier;
import com.kbf.management.repository.SupplierRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository repo;

    public List<SupplierDto> getAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public SupplierDto getById(Long id) {
        Supplier s = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + id));
        return toDto(s);
    }

    @Transactional
    public SupplierDto create(SupplierDto dto) {
        Supplier s = new Supplier();
        s.setName(dto.getName());
        s.setContact(dto.getContact());
        s.setAddress(dto.getAddress());
        s.setEmail(dto.getEmail());
        s.setPhone(dto.getPhone());
        Supplier saved = repo.save(s);
        return toDto(saved);
    }

    @Transactional
    public SupplierDto update(Long id, SupplierDto dto) {
        Supplier s = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + id));
        s.setName(dto.getName());
        s.setContact(dto.getContact());
        s.setAddress(dto.getAddress());
        s.setEmail(dto.getEmail());
        s.setPhone(dto.getPhone());
        Supplier updated = repo.save(s);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        Supplier s = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + id));
        repo.delete(s);
    }

    private SupplierDto toDto(Supplier s) {
        SupplierDto dto = new SupplierDto();
        dto.setId(s.getSupplierId());
        dto.setName(s.getName());
        dto.setContact(s.getContact());
        dto.setAddress(s.getAddress());
        dto.setEmail(s.getEmail());
        dto.setPhone(s.getPhone());
       // dto.setProvenderIds(s.getProvenders().stream().map(p -> p.getProvenderId()).collect(Collectors.toList()));
      //  dto.setProbioticApplicationIds(s.getProbiotics().stream().map(pa -> pa.getProbioticId()).collect(Collectors.toList()));       
        return dto;
    }
}