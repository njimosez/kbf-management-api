package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.NutrientRequirement;

public interface NutrientRespository extends JpaRepository<NutrientRequirement, Long> {

}
