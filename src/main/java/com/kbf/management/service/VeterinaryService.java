package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Veterinary;
import com.kbf.management.repository.VeterinaryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinaryService {

    @Autowired
    private VeterinaryRepository repository;

    public List<Veterinary> getAll() {
        return repository.findAll();
    }

    public Optional<Veterinary> getById(Long id) {
        return repository.findById(id);
    }

    public Veterinary save(Veterinary obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
