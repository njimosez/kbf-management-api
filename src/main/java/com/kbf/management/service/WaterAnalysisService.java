package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.WaterAnalysis;
import com.kbf.management.repository.WaterAnalysisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WaterAnalysisService {

    @Autowired
    private WaterAnalysisRepository repository;

    public List<WaterAnalysis> getAll() {
        return repository.findAll();
    }

    public Optional<WaterAnalysis> getById(Long id) {
        return repository.findById(id);
    }

    public WaterAnalysis save(WaterAnalysis obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
