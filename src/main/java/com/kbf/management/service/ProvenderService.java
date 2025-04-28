package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Provender;
import com.kbf.management.repository.ProvenderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProvenderService {

    @Autowired
    private ProvenderRepository repository;

    public List<Provender> getAllProvenders() {
        return repository.findAll();
    }

    public Provender saveProvender(Provender provender) {
        return repository.save(provender);
    }
    public Optional<Provender> getById(Long id) {
        return repository.findById(id);
    }

    public Provender save(Provender obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
