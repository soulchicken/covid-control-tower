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

    public Danger save(DangerDTO dangerDTO) {
        Optional<Patient> patient = patientRepository.findById(dangerDTO.getPatientId());
        Optional<HospitalRoom> hospitalRoom = hospitalRoomRepository.findById(dangerDTO.getHospitalRoomnumberId());
        Danger newDanger = Danger
                .builder()
                .patient(patient.get())
                .dangerId(dangerDTO.getDangerId())
                .dangerCareDate(dangerDTO.getDangerCareDate())
                .dangerCareRelease(dangerDTO.getDangerCareRelease())
                .hospitalRoom(hospitalRoom.get())
                .build();
        Danger mDanger = repository.save(newDanger);
        return mDanger;
    }

    public List<Danger> update(Danger danger) {
        final Optional<Danger> findDanger = repository.findById(danger.getDangerId());
        findDanger.ifPresent(newDanger -> {
            newDanger.setDangerCareDate(danger.getDangerCareDate());
            newDanger.setDangerCareRelease(danger.getDangerCareRelease());


            repository.save(newDanger);
        });
        return repository.findAll();
    }

    public List<Danger> delete(Long id) {
        final Optional<Danger> findDanger = repository.findById(id);

        findDanger.ifPresent(danger -> {
            // programList : 삭제하고자 하는 엔터티
            repository.delete(danger);
        });

        return repository.findAll();
    }

    public DangerDTO dangerDTO(Danger danger){
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