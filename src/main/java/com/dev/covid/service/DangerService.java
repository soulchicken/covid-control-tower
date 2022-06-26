package com.dev.covid.service;

import com.dev.covid.DTO.DangerDTO;
import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.model.Danger;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.model.Manager;
import com.dev.covid.model.Patient;
import com.dev.covid.repository.DangerRepository;
import com.dev.covid.repository.HospitalRoomRepository;
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

    @Autowired
    private HospitalRoomRepository hospitalRoomRepository;

    public List<Danger> findAll() {
        return repository.findAll();
    }


    public Danger save(DangerDTO dangerDTO) throws Exception {
        Patient patient = patientRepository.findById(dangerDTO.getPatientId()).orElseThrow(Exception::new);
        HospitalRoom hospitalRoom = hospitalRoomRepository.findById(dangerDTO.getHospitalRoomnumberId()).orElseThrow(Exception::new);
        Danger newDanger = Danger
                .builder()
                .patient(patient)
                .dangerId(dangerDTO.getDangerId())
                .dangerCareDate(dangerDTO.getDangerCareDate())
                .dangerCareRelease(dangerDTO.getDangerCareRelease())
                .hospitalRoom(hospitalRoom)
                .build();
        return repository.save(newDanger);
    }


    public Danger update(DangerDTO dangerDTO) throws Exception {
        final Danger findDanger = repository.findById(dangerDTO.getDangerId()).orElseThrow(Exception::new);

        findDanger.setDangerCareDate(dangerDTO.getDangerCareDate());
        findDanger.setDangerCareRelease(dangerDTO.getDangerCareRelease());

        return repository.save(findDanger);

    }

    public Danger delete(Long id) throws Exception {
        final Danger findDanger = repository.findById(id).orElseThrow(Exception::new);
        repository.delete(findDanger);
        return findDanger;
    }

    public DangerDTO dangerDTO(Danger danger) {
        return DangerDTO
                .builder()
                .dangerId(danger.getDangerId())
                .dangerCareDate(danger.getDangerCareDate())
                .dangerCareRelease(danger.getDangerCareRelease())
                .hospitalRoomnumberId(danger.getHospitalRoom().getHospitalroomRoomnumber())
                .patientId(danger.getPatient().getPeopleId())
                .build();
    }
}