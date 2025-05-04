package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

}
