package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
