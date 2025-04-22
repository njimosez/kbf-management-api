package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Pond;
import com.kbf.management.repository.PondRepository;

import java.util.List;

@Service
public class PondService {

    @Autowired
    private PondRepository fishPondRepository;

    public List<Pond> getAllPonds() {
        return fishPondRepository.findAll();
    }

    public Pond savePond(Pond pond) {
        return fishPondRepository.save(pond);
    }
}
