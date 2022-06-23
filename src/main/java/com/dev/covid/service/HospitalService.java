package com.dev.covid.service;

import com.dev.covid.model.Hospital;
import com.dev.covid.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository repository;

    public List<Hospital> findAll() {
        return repository.findAll();
    }

    public Hospital save(Hospital hospital) {
        return repository.save(hospital);
    }

    public List<Hospital> update(Hospital hospital) {
        final Optional<Hospital> findHospital = repository.findById((hospital.getHospitalId()));

        findHospital.ifPresent(updateHospital -> {
            updateHospital.setHospitalName(hospital.getHospitalName());
            updateHospital.setHospitalPatientnum((hospital.getHospitalPatientnum()));
            updateHospital.setHospitalRoom(hospital.getHospitalRoom());
            updateHospital.setHospitalRoomlimit(hospital.getHospitalRoomlimit());

            repository.save(updateHospital);
        });
        return repository.findAll();
    }

    public List<Hospital> delete(Long id) {
        final Optional<Hospital> findHospital = repository.findById(id);

        findHospital.ifPresent(hospital -> {
            repository.delete(hospital);
        });
        return repository.findAll();
    }
}
