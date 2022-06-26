package com.dev.covid.service;

import com.dev.covid.DTO.InfectionTrackingDTO;
import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.DTO.SelfQuarantineDTO;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.Manager;
import com.dev.covid.model.Patient;
import com.dev.covid.repository.HospitalRepository;
import com.dev.covid.repository.ManagerRepository;
import com.dev.covid.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ManagerRepository managerRepository;

    public Patient save(PatientDTO patientDTO) throws Exception {

        Manager manager = managerRepository.findById(patientDTO.getManagerId()).orElseThrow(Exception::new);
        Hospital hospital = hospitalRepository.findById(patientDTO.getHospitalId()).orElseThrow(Exception::new);

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


    public Patient findById(Long id) throws Exception {
        Patient foundPatient = patientRepository.findById(id).orElseThrow(Exception::new);
        return foundPatient;
    }

    public PatientDTO makePatientDTO(Patient patient, SelfQuarantineDTO selfQuarantineDTO){
        return PatientDTO
                .builder()
                .peopleAge(patient.getPeopleAge())
                .peopleGender(patient.getPeopleGender())
                .peopleHome(patient.getPeopleHome())
                .managerId(patient.getManager().getManagerId())
                .peopleId(patient.getPeopleId())
                .peopleName(patient.getPeopleName())
                .peoplePhone(patient.getPeoplePhone())
                .selfQuarantineDTO(selfQuarantineDTO)
                .hospitalId(patient.getHospital().getHospitalId())
                .build();
    }

    public PatientDTO makePatientDTO(Patient patient){
        return PatientDTO
                .builder()
                .peopleAge(patient.getPeopleAge())
                .peopleGender(patient.getPeopleGender())
                .peopleHome(patient.getPeopleHome())
                .managerId(patient.getManager().getManagerId())
                .peopleId(patient.getPeopleId())
                .peopleName(patient.getPeopleName())
                .peoplePhone(patient.getPeoplePhone())
                .hospitalId(patient.getHospital().getHospitalId())
                .build();
    }

    public PatientDTO makePatientDTO(Patient patient, SelfQuarantineDTO selfQuarantineDTO, List<InfectionTrackingDTO> infectionTrackingDTOList){
        return PatientDTO
                .builder()
                .peopleAge(patient.getPeopleAge())
                .peopleGender(patient.getPeopleGender())
                .peopleHome(patient.getPeopleHome())
                .managerId(patient.getManager().getManagerId())
                .peopleId(patient.getPeopleId())
                .peopleName(patient.getPeopleName())
                .peoplePhone(patient.getPeoplePhone())
                .selfQuarantineDTO(selfQuarantineDTO)
                .infectionTrackingDTOList(infectionTrackingDTOList)
                .hospitalId(patient.getHospital().getHospitalId())
                .build();
    }

    public PatientDTO makePatientDTO(Patient patient, List<InfectionTrackingDTO> infectionTrackingDTOList){
        return PatientDTO
                .builder()
                .peopleAge(patient.getPeopleAge())
                .peopleGender(patient.getPeopleGender())
                .peopleHome(patient.getPeopleHome())
                .managerId(patient.getManager().getManagerId())
                .peopleId(patient.getPeopleId())
                .peopleName(patient.getPeopleName())
                .peoplePhone(patient.getPeoplePhone())
                .infectionTrackingDTOList(infectionTrackingDTOList)
                .hospitalId(patient.getHospital().getHospitalId())
                .build();
    }
}
