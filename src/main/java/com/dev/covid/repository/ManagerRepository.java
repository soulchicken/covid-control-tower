package com.dev.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.covid.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{

}
