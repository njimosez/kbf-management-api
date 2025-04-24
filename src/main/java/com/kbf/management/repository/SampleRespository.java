package com.kbf.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.management.model.Sample;

public interface SampleRespository extends JpaRepository<Sample, Long> {

}
