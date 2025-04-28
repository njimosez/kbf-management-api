/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.AnimalStock;
import com.kbf.management.repository.AnimalStockRepository;

@Service
public class AnimalStockService {

    @Autowired
    private AnimalStockRepository repository;

    public List<AnimalStock> getAnimalStocks() {
        return repository.findAll();
    }

    public AnimalStock saveAnimalStock(AnimalStock AnimalStock) {
        return repository.save(AnimalStock);
    }
    
    public Optional<AnimalStock> getById(Long id) {
        return repository.findById(id);
    }

    public AnimalStock save(AnimalStock obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
