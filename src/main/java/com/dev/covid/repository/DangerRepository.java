package com.dev.covid.repository;

import com.dev.covid.model.Danger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DangerRepository extends JpaRepository<Danger, Long> {

}
