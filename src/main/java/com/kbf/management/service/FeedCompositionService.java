/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kbf.management.dto.FeedCompositionDto;
import com.kbf.management.model.FeedComposition;
import com.kbf.management.model.Ingredient;
import com.kbf.management.model.Provender;
import com.kbf.management.repository.FeedCompositionRepository;
import com.kbf.management.repository.IngredientRepository;
import com.kbf.management.repository.ProvenderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedCompositionService {

	private final FeedCompositionRepository compositionRepo;
    private final ProvenderRepository provenderRepo;
    private final IngredientRepository ingredientRepo;

    public List<FeedCompositionDto> getAll() {
        return compositionRepo.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public FeedCompositionDto getById(Long id) {
        FeedComposition comp = compositionRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FeedComposition not found: " + id));
        return toDto(comp);
    }

    @Transactional
    public FeedCompositionDto create(FeedCompositionDto dto) {
        Provender p = provenderRepo.findById(dto.getProvenderId())
            .orElseThrow(() -> new IllegalArgumentException("Provender not found: " + dto.getProvenderId()));
        Ingredient ing = ingredientRepo.findById(dto.getIngredientId())
            .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + dto.getIngredientId()));

        FeedComposition comp = new FeedComposition();
        comp.setPercentage(dto.getPercentage());
        comp.setProtein(dto.getProtein());
        comp.setFat(dto.getFat());
        comp.setFiber(dto.getFiber());
        comp.setEnergy(dto.getEnergy());
        comp.setPremix(dto.isPremix());
        comp.setMoistureContent(dto.getMoistureContent());
        comp.setAshPercentage(dto.getAshPercentage());
        comp.setIngredient(ing);
        comp.setProvender(p);

        FeedComposition saved = compositionRepo.save(comp);
        return toDto(saved);
    }

    @Transactional
    public FeedCompositionDto update(Long id, FeedCompositionDto dto) {
        FeedComposition comp = compositionRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FeedComposition not found: " + id));

        comp.setPercentage(dto.getPercentage());
        comp.setProtein(dto.getProtein());
        comp.setFat(dto.getFat());
        comp.setFiber(dto.getFiber());
        comp.setEnergy(dto.getEnergy());
        comp.setPremix(dto.isPremix());
        comp.setMoistureContent(dto.getMoistureContent());
        comp.setAshPercentage(dto.getAshPercentage());
        // associations unchanged

        FeedComposition updated = compositionRepo.save(comp);
        return toDto(updated);
    }

    @Transactional
    public void delete(Long id) {
        FeedComposition comp = compositionRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("FeedComposition not found: " + id));
        compositionRepo.delete(comp);
    }

    private FeedCompositionDto toDto(FeedComposition comp) {
        FeedCompositionDto dto = new FeedCompositionDto();
        dto.setId(comp.getFeedCompId());
        dto.setPercentage(comp.getPercentage());
        dto.setProtein(comp.getProtein());
        dto.setFat(comp.getFat());
        dto.setFiber(comp.getFiber());
        dto.setEnergy(comp.getEnergy());
        dto.setPremix(comp.isPremix());
        dto.setMoistureContent(comp.getMoistureContent());
        dto.setAshPercentage(comp.getAshPercentage());
        dto.setIngredientId(comp.getIngredient().getId());
        dto.setProvenderId(comp.getProvender().getProvenderId());
        return dto;
    }
}
