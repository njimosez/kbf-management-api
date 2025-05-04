package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
