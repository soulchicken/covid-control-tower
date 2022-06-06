package com.dev.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.covid.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
