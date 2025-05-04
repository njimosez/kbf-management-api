package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.PersonnelDto;
import com.kbf.management.model.Personnel;
import com.kbf.management.repository.PayrollRepository;
import com.kbf.management.repository.PersonnelRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonnelService {

    private final PersonnelRepository personnelRepo;
    private final PayrollRepository payrollRepo;

    public List<PersonnelDto> getAll() {
        return personnelRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public PersonnelDto getById(Long id) {
        Personnel p = personnelRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Personnel not found: " + id));
        return toDto(p);
    }

    @Transactional
    public PersonnelDto create(PersonnelDto dto) {
        Personnel p = new Personnel();
        p.setName(dto.getName());
        p.setRole(dto.getRole());
        p.setContact(dto.getContact());
        p.setShift(dto.getShift());
        p.setStatus(dto.getStatus());
        p.setActive(dto.getIsActive());
        p.setSalary(dto.getSalary());
        p.setSavings(dto.getSavings());
        p.setNetPerceived(dto.getNetPerceived());
        Personnel saved = personnelRepo.save(p);
        return toDto(saved);
    }

    @Transactional
    public PersonnelDto update(Long id, PersonnelDto dto) {
        Personnel p = personnelRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Personnel not found: " + id));
        p.setName(dto.getName());
        p.setRole(dto.getRole());
        p.setContact(dto.getContact());
        p.setShift(dto.getShift());
        p.setStatus(dto.getStatus());
        p.setActive(dto.getIsActive());
        p.setSalary(dto.getSalary());
        p.setSavings(dto.getSavings());
        p.setNetPerceived(dto.getNetPerceived());
        Personnel updated = personnelRepo.save(p);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        Personnel p = personnelRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Personnel not found: " + id));
        personnelRepo.delete(p);
    }

    private PersonnelDto toDto(Personnel p) {
        PersonnelDto dto = new PersonnelDto();
        dto.setId(p.getPersonnelId());
        dto.setName(p.getName());
        dto.setRole(p.getRole());
        dto.setContact(p.getContact());
        dto.setShift(p.getShift());
        dto.setStatus(p.getStatus());
        dto.setIsActive(p.isActive());
        dto.setSalary(p.getSalary());
        dto.setSavings(p.getSavings());
        dto.setNetPerceived(p.getNetPerceived());
        dto.setPayrollIds(p.getPayrolls().stream().map(pay -> pay.getPayId()).collect(Collectors.toList()));
        return dto;
    }
}