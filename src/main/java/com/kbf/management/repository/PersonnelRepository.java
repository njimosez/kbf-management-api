package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

}
