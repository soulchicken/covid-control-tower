package com.dev.covid.repository;

import com.dev.covid.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Patient, Long> {
}
