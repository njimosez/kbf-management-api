package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

}
