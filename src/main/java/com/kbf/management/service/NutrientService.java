/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Nutrient;
import com.kbf.management.repository.NutrientRepository;

@Service
public class NutrientService {

    @Autowired
    private NutrientRepository repository;

    public List<Nutrient> getNutrients() {
        return repository.findAll();
    }

    public Nutrient saveNutrient(Nutrient nutrient) {
        return repository.save(nutrient);
    }
    
    public Optional<Nutrient> getById(Long id) {
        return repository.findById(id);
    }

    public Nutrient save(Nutrient obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
