package com.dev.covid.repository;

import com.dev.covid.model.Danger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DangerRepository extends JpaRepository<Danger, Long> {


    List<Danger> findBydangerCareDateBetween(LocalDate startDate, LocalDate endDate);

    List<Danger> findBydangerCareReleaseBetween(LocalDate startDate, LocalDate endDate);
}
