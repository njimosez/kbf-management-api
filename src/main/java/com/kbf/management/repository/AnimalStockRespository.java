package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.AnimalStock;

public interface AnimalStockRespository extends JpaRepository<AnimalStock, Long> {

}
