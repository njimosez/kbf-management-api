/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.FeedComposition;
import com.kbf.management.repository.FeedCompositionRepository;

@Service
public class FeedCompositionService {

    @Autowired
    private FeedCompositionRepository repository;

    public List<FeedComposition> getFeedCompositions() {
        return repository.findAll();
    }

    public FeedComposition saveFeedComposition(FeedComposition feedComposition) {
        return repository.save(feedComposition);
    }
    
    public Optional<FeedComposition> getById(Long id) {
        return repository.findById(id);
    }

    public FeedComposition save(FeedComposition obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
