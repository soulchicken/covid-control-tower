package com.dev.covid.service;

import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.model.Manager;
import com.dev.covid.model.Patient;
import com.dev.covid.repository.ManagerRepository;
import com.dev.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class PatientService implements Service {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Patient save(PatientDTO patientDTO) throws Exception {
        Optional<Manager> foundManager = managerRepository.findById(patientDTO.getManagerId());
        Patient patient = new Patient();
        foundManager.ifPresent(manager -> {
            patient.setManager(manager);
                }
        );

        patient.setPeoplePhone(patientDTO.getPeoplePhone());
        patient.setPeopleHome(patientDTO.getPeopleHome());
        patient.setPeopleGender(patientDTO.getPeopleGender());
        patient.setPeopleName(patientDTO.getPeopleName());
        patient.setPeopleAge(patientDTO.getPeopleAge());
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
    public List<Patient> delete(Long id) throws Exception {
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
            newPatient.setManager(patient.getManager());
            newPatient.setPeoplePhone(patient.getPeoplePhone());
            newPatient.setSelfQuarantine(patient.getSelfQuarantine());
            newPatient.setPeopleId(patient.getPeopleId());
            newPatient.setPeopleIsDanger(patient.getPeopleIsDanger());
        });
        return newPatient;
    }

}
