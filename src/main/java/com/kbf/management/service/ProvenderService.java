package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Provender;
import com.kbf.management.repository.ProvenderRepository;

import java.util.List;

@Service
public class ProvenderService {

    @Autowired
    private ProvenderRepository provenderRepository;

    public List<Provender> getAllProvenders() {
        return provenderRepository.findAll();
    }

    public Provender saveProvender(Provender provender) {
        return provenderRepository.save(provender);
    }
}
