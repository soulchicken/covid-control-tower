package com.dev.covid.service;

import com.dev.covid.model.Patient;
import com.dev.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class PatientService implements Service {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient update(Patient patient) {
        Patient foundPatient = patientRepository.findByPeopleName(patient.getPeopleName());

        foundPatient.setPeopleAge(patient.getPeopleAge());
        foundPatient.setPeopleGender(patient.getPeopleGender());
        foundPatient.setPeopleHome(patient.getPeopleHome());
        foundPatient.setPeopleName(patient.getPeopleName());
        foundPatient.setPeoplePhone(patient.getPeoplePhone());
        foundPatient.setPeopleIsDanger(patient.getPeopleIsDanger());

        return patientRepository.save(foundPatient);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> delete(Long id) {
        final Optional<Patient> foundPatient = patientRepository.findById(id);

        foundPatient.ifPresent(patient -> {
            patientRepository.delete(patient);
        });
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Long id) {
        final Optional<Patient> foundPatient = patientRepository.findById(id);
        Patient newPatient = new Patient();
        foundPatient.ifPresent(patient -> {
            newPatient.setPeopleAge(patient.getPeopleAge());
            newPatient.setPeopleName(patient.getPeopleName());
            newPatient.setPeopleGender(patient.getPeopleGender());
            newPatient.setPeopleHome(patient.getPeopleHome());
            newPatient.setPeoplePhone(patient.getPeoplePhone());
            newPatient.setSelfQuarantine(patient.getSelfQuarantine());
            newPatient.setPeopleId(patient.getPeopleId());
            newPatient.setPeopleIsDanger(patient.getPeopleIsDanger());
        });
        return newPatient;
    }

}
