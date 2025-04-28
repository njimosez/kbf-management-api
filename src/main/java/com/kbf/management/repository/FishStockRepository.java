package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.FishStock;

public interface FishStockRepository extends JpaRepository<FishStock, Long> {

}
