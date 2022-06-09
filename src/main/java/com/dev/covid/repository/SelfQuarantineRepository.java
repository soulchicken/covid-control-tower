package com.dev.covid.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.covid.model.SelfQuarantine;

public interface SelfQuarantineRepository extends JpaRepository<SelfQuarantine, Long> {

	List<SelfQuarantine> findByselfQuarantineDateBetween(Date startDate, Date endDate);

	List<SelfQuarantine> findByselfQuarantineReleaseBetween(Date startDate, Date endDate);

	List<SelfQuarantine> findByselfQuarantineName(String name);


}
