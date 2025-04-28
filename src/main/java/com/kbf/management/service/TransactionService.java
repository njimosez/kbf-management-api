/**
 * Test
 */
package com.kbf.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.management.model.Transaction;
import com.kbf.management.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }
    
    public Optional<Transaction> getById(Long id) {
        return repository.findById(id);
    }

    public Transaction save(Transaction obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public List<Transaction> getTransactionsFromLastMonths(int months) {
        String interval = months + " months"; // ✅ create valid interval string
        return repository.findTransactionsFromInterval(interval);
    }
}
