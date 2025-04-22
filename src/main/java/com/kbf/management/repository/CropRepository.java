package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Crop;

public interface CropRepository extends JpaRepository<Crop, Long> {

}
