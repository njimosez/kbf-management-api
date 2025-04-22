package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
