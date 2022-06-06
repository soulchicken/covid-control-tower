package com.dev.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.covid.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long>{

}
