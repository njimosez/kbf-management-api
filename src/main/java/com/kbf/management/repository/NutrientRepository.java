package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Nutrient;

public interface NutrientRepository extends JpaRepository<Nutrient, Long> {

}
