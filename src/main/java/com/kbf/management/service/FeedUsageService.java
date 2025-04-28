/**
 * Test
 */
package com.kbf.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.FeedUsage;
import com.kbf.management.repository.FeedUsageRepository;

@Service
public class FeedUsageService {

    @Autowired
    private FeedUsageRepository repository;

    public List<FeedUsage> getFeedUsages() {
        return repository.findAll();
    }

    public FeedUsage saveFeedUsage(FeedUsage feedUsage) {
        return repository.save(feedUsage);
    }
    
    public Optional<FeedUsage> getById(Long id) {
        return repository.findById(id);
    }

    public FeedUsage save(FeedUsage obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
