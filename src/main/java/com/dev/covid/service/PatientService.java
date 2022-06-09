package com.dev.covid.service;

import com.dev.covid.model.Patient;
import com.dev.covid.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class PatientService implements Service {
    @Autowired
    private Repository repository;

    @Override
    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public List<Patient> update(Patient patient) {
        final Optional<Patient> foundPatient = repository.findById(patient.getPeopleId());

        foundPatient.ifPresent(newPatient -> {
            newPatient.setPeopleAge(patient.getPeopleAge());
            newPatient.setPeopleGender(patient.getPeopleGender());
            newPatient.setPeopleHome(patient.getPeopleHome());
            newPatient.setPeopleName(patient.getPeopleName());
            newPatient.setPeoplePhone(patient.getPeoplePhone());
            newPatient.setPeopleIsDanger(patient.getPeopleIsDanger());

            repository.save(newPatient);
        });
        return repository.findAll();
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Patient> delete(Long id) {
        final Optional<Patient> foundPatient = repository.findById(id);

        foundPatient.ifPresent(patient -> {
            repository.delete(patient);
        });
        return repository.findAll();
    }

}
