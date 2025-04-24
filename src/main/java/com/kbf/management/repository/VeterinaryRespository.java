package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Veterinary;

public interface VeterinaryRespository extends JpaRepository<Veterinary, Long> {

}
