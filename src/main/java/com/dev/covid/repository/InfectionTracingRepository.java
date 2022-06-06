package com.dev.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.covid.model.InfectionTracking;

public interface InfectionTracingRepository extends JpaRepository<InfectionTracking, Long>{

}
