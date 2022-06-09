package com.dev.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.covid.model.SelfQuarantine;

public interface SelfQuarantineRepository extends JpaRepository<SelfQuarantine, Long> {

}
