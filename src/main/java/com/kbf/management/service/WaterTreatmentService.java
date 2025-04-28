package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.WaterTreatment;
import com.kbf.management.repository.WaterTreatmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WaterTreatmentService {

    @Autowired
    private WaterTreatmentRepository repository;

    public List<WaterTreatment> getAll() {
        return repository.findAll();
    }

    public Optional<WaterTreatment> getById(Long id) {
        return repository.findById(id);
    }

    public WaterTreatment save(WaterTreatment obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
