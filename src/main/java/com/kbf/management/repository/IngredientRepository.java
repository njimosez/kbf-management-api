package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
