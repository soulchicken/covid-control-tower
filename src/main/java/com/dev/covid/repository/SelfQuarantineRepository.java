package com.dev.covid.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.covid.model.SelfQuarantine;

public interface SelfQuarantineRepository extends JpaRepository<SelfQuarantine, Long> {

	List<SelfQuarantine> findByselfQuarantineDateBetween(Date startDate, Date endDate);

}
