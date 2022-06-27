package com.dev.covid.repository;

import com.dev.covid.model.InfectionTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfectionTrackingRepository extends JpaRepository<InfectionTracking, Long> {
}
