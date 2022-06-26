package com.dev.covid.service;

import com.dev.covid.DTO.DangerDTO;
import com.dev.covid.model.Danger;

import com.dev.covid.model.HospitalRoom;
import com.dev.covid.model.Manager;

import com.dev.covid.model.Patient;
import com.dev.covid.repository.DangerRepository;
import com.dev.covid.repository.HospitalRoomRepository;
import com.dev.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<Danger> findBydangerCareDateBetween(String start, String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        System.out.println("스타트 데이트 "+start);
        System.out.println("엔드 데이트 "+end);
        return repository.findBydangerCareDateBetween(startDate,endDate);

    }

    public List<Danger> findBydangerCareReleaseBetween(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return repository.findBydangerCareReleaseBetween(startDate,endDate);

    }

    public Danger findById(Long id){
        try{
            Danger danger = repository.findById(id).orElseThrow(Exception::new);
            return danger;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}