package com.dev.covid.service;

import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.model.Patient;

import java.util.List;

public interface Service {
    Patient save(PatientDTO patientDTO);

    Patient update(Patient patient);

    List<Patient> findAll();

    List<Patient> delete(Long id);

    Patient findById(Long id);
}
