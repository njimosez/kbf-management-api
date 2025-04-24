package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.WaterAnalysis;

public interface WaterTreatmentRespository extends JpaRepository<WaterAnalysis, Long> {

}
