/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Sample;
import com.kbf.management.repository.SampleRepository;

@Service
public class SampleService {

    @Autowired
    private SampleRepository repository;

    public List<Sample> getSamples() {
        return repository.findAll();
    }

    public Sample saveSample(Sample sample) {
        return repository.save(sample);
    }
    
    public Optional<Sample> getById(Long id) {
        return repository.findById(id);
    }

    public Sample save(Sample obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
