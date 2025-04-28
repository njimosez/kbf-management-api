/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.FishStock;
import com.kbf.management.repository.FishStockRepository;

@Service
public class FishStockService {

    @Autowired
    private FishStockRepository repository;

    public List<FishStock> getFishStocks() {
        return repository.findAll();
    }

    public FishStock saveFishStock(FishStock fishStock) {
        return repository.save(fishStock);
    }
    
    public Optional<FishStock> getById(Long id) {
        return repository.findById(id);
    }

    public FishStock save(FishStock obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
