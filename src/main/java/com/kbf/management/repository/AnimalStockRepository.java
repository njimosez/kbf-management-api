package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.AnimalStock;

public interface AnimalStockRepository extends JpaRepository<AnimalStock, Long> {

}
