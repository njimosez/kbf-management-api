/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Ingredient;
import com.kbf.management.repository.IngredientRepository;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public List<Ingredient> getIngredients() {
        return repository.findAll();
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }
    
    public Optional<Ingredient> getById(Long id) {
        return repository.findById(id);
    }

    public Ingredient save(Ingredient obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
