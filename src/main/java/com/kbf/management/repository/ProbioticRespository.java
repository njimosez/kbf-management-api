package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Probiotics;

public interface ProbioticRespository extends JpaRepository<Probiotics, Long> {

}
