package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.PondDto;
import com.kbf.management.model.Pond;
import com.kbf.management.repository.PondRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PondService {

	private final PondRepository pondRepo;

    public List<PondDto> getAll() {
        return pondRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public PondDto getById(Long id) {
        Pond pond = pondRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + id));
        return toDto(pond);
    }

    @Transactional
    public PondDto create(PondDto dto) {
        Pond pond = toEntity(dto);
        Pond saved = pondRepo.save(pond);
        return toDto(saved);
    }

    @Transactional
    public PondDto update(Long id, PondDto dto) {
        Pond pond = pondRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + id));
        pond.setName(dto.getName());
        pond.setSize(dto.getSize());
        pond.setLocation(dto.getLocation());
        pond.setStatus(dto.getStatus());
        pond.setFishCapacity(dto.getFishCapacity());
        Pond updated = pondRepo.save(pond);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        Pond pond = pondRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pond not found: " + id));
        pondRepo.delete(pond);
    }

    private PondDto toDto(Pond pond) {
        PondDto dto = new PondDto();
        dto.setName(pond.getName());
        dto.setSize(pond.getSize());
        dto.setLocation(pond.getLocation());
        dto.setStatus(pond.getStatus());
        dto.setFishCapacity(pond.getFishCapacity());
        return dto;
    }

    private Pond toEntity(PondDto dto) {
        Pond pond = new Pond();
        pond.setName(dto.getName());
        pond.setSize(dto.getSize());
        pond.setLocation(dto.getLocation());
        pond.setStatus(dto.getStatus());
        pond.setFishCapacity(dto.getFishCapacity());
        return pond;
    }
}