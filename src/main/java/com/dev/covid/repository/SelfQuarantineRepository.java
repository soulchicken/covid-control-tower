package com.dev.covid.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.covid.model.SelfQuarantine;

public interface SelfQuarantineRepository extends JpaRepository<SelfQuarantine, Long> {

	List<SelfQuarantine> findByselfQuarantineDateBetween(LocalDate startDate, LocalDate endDate);

	List<SelfQuarantine> findByselfQuarantineReleaseBetween(LocalDate startDate, LocalDate endDate);

	List<SelfQuarantine> findByselfQuarantineName(String name);


}
