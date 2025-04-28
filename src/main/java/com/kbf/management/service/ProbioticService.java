package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Probiotic;
import com.kbf.management.repository.ProbioticRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProbioticService {

    @Autowired
    private ProbioticRepository repository;

    public List<Probiotic> getAll() {
        return repository.findAll();
    }

    public Optional<Probiotic> getById(Long id) {
        return repository.findById(id);
    }

    public Probiotic save(Probiotic obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
