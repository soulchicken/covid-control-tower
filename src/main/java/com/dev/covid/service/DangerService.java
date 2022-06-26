package com.dev.covid.service;

import com.dev.covid.DTO.DangerDTO;
import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.model.Danger;
import com.dev.covid.model.Manager;
import com.dev.covid.model.Patient;
import com.dev.covid.repository.DangerRepository;
import com.dev.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DangerService {
    @Autowired
    private DangerRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Danger> findAll() {
        return repository.findAll();
    }

    public Danger save(DangerDTO dangerDTO) throws Exception{
       Patient patient = patientRepository.findById(dangerDTO.getPatientId()).get();
        Danger newDanger = Danger
                .builder()
                .patient(patient)
                .dangerId(dangerDTO.getDangerId())
                .dangerCareDate(dangerDTO.getDangerCareDate())
                .dangerCareRelease(dangerDTO.getDangerCareRelease())
                .hospitalRoomnumber(dangerDTO.getHospitalRoomnumber())
                .build();
        return repository.save(newDanger);
    }

    public Danger update(DangerDTO dangerDTO) throws Exception{
        final Danger findDanger = repository.findById(dangerDTO.getDangerId()).get();

        findDanger.setDangerCareDate(dangerDTO.getDangerCareDate());
        findDanger.setDangerCareRelease(dangerDTO.getDangerCareRelease());

            return repository.save(findDanger);

    }

    public Danger delete(Long id) {
       final Danger findDanger = repository.findById(id).get();
            repository.delete(findDanger);
        return findDanger;
    }

    public DangerDTO dangerDTO(Danger danger){
        return DangerDTO
                .builder()
                .dangerId(danger.getDangerId())
                .dangerCareDate(danger.getDangerCareDate())
                .dangerCareRelease(danger.getDangerCareRelease())
                .hospitalRoomnumber(danger.getHospitalRoomnumber())
                .patientId(danger.getPatient().getPeopleId())
                .build();
    }
}