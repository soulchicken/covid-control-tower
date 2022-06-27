package com.dev.covid.repository;

import com.dev.covid.model.HospitalRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRoomRepository extends JpaRepository<HospitalRoom, Long> {
}
