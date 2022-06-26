package com.dev.covid.service;

import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.Manager;
import com.dev.covid.model.Patient;
import com.dev.covid.repository.HospitalRepository;
import com.dev.covid.repository.ManagerRepository;
import com.dev.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    public Patient save(PatientDTO patientDTO) throws Exception {
        Manager manager = managerRepository.findById(patientDTO.getManagerId()).get();
        Hospital hospital = hospitalRepository.findById(patientDTO.getHospitalId()).get();
        Patient patient = new Patient();
        patient.setManager(manager);
        patient.setPeoplePhone(patientDTO.getPeoplePhone());
        patient.setPeopleHome(patientDTO.getPeopleHome());
        patient.setPeopleGender(patientDTO.getPeopleGender());
        patient.setPeopleName(patientDTO.getPeopleName());
        patient.setPeopleAge(patientDTO.getPeopleAge());
        patient.setHospital(hospital);
        return patientRepository.save(patient);
    }

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


    public List<Patient> findAll() {
        return patientRepository.findAll();
    }


    public List<Patient> delete(Long id) throws Exception {
        final Optional<Patient> foundPatient = patientRepository.findById(id);

        foundPatient.ifPresent(patient -> {
            patientRepository.delete(patient);
        });
        return patientRepository.findAll();
    }


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
