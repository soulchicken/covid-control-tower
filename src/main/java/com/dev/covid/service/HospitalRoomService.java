package com.dev.covid.service;

import com.dev.covid.model.Hospital;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.repository.HospitalRepository;
import com.dev.covid.repository.HospitalRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalRoomService {
    @Autowired
    private HospitalRoomRepository repository;

    public List<HospitalRoom> findAll() {
        return repository.findAll();
    }

    public HospitalRoom save(HospitalRoom hospitalroom) {
        return repository.save(hospitalroom);
    }

    public List<HospitalRoom> update(HospitalRoom hospitalroom) {
        final Optional<HospitalRoom> findHospitalRoom = repository.findById(hospitalroom.getHospitalhospitalId());

        findHospitalRoom.ifPresent(updateHospitalRoom -> {
            updateHospitalRoom.setHospitalroomRoomnumber(hospitalroom.getHospitalroomRoomnumber());
            updateHospitalRoom.setHospitalroomCapacity(hospitalroom.getHospitalroomCapacity());

            repository.save(updateHospitalRoom);
        });
        return repository.findAll();
    }

    public List<HospitalRoom> delete(Long id) {
        final Optional<HospitalRoom> findHospitalRoom = repository.findById(id);

        findHospitalRoom.ifPresent(hospitalroom -> {
            repository.delete(hospitalroom);
        });
        return repository.findAll();
    }
}
