package com.dev.covid.service;

import com.dev.covid.DTO.InfectionTrackingDTO;
import com.dev.covid.model.InfectionTracking;
import com.dev.covid.model.Patient;
import com.dev.covid.repository.InfectionTrackingRepository;
import com.dev.covid.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InfectionTrackingService {

    @Autowired
    private InfectionTrackingRepository infectionTrackingRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<InfectionTracking> findAll() {
        return infectionTrackingRepository.findAll();
    }

    public InfectionTracking save(InfectionTrackingDTO infectionTrackingDTO) throws Exception {
            Patient patient = patientRepository.findById(infectionTrackingDTO.getPatientPeopleId()).orElseThrow(Exception::new);
            InfectionTracking infectionTracking =  makeInfectionTracking(patient, infectionTrackingDTO);
            return infectionTrackingRepository.save(infectionTracking);
    }

    public InfectionTracking update(InfectionTrackingDTO infectionTrackingDTO) throws Exception {
            final InfectionTracking findInfectionTracking = infectionTrackingRepository.findById(infectionTrackingDTO.getInfectionTrackingId()).orElseThrow(Exception::new);

            findInfectionTracking.setInfectionTrackingArea(infectionTrackingDTO.getInfectionTrackingArea());
            findInfectionTracking.setInfectionTrackingDate(infectionTrackingDTO.getInfectionTrackingDate());
            findInfectionTracking.setInfectionTrackingCause(infectionTrackingDTO.getInfectionTrackingCause());

            return infectionTrackingRepository.save(findInfectionTracking);
    }

    public List<InfectionTracking> delete(Long id) throws Exception{

        InfectionTracking findInfectionTracking = infectionTrackingRepository.findById(id).orElseThrow(Exception::new);
        infectionTrackingRepository.delete(findInfectionTracking);
        return infectionTrackingRepository.findAll();

    }

    public InfectionTrackingDTO makeInfectionTrackingDTO(InfectionTracking infectionTracking){

        return InfectionTrackingDTO
                .builder()
                .infectionTrackingArea(infectionTracking.getInfectionTrackingArea())
                .infectionTrackingCause(infectionTracking.getInfectionTrackingCause())
                .infectionTrackingDate(infectionTracking.getInfectionTrackingDate())
                .infectionTrackingId(infectionTracking.getInfectionTrackingId())
                .patientPeopleId(infectionTracking.getPatient().getPeopleId())
                .build();
    }

    public List<InfectionTrackingDTO> makeInfectionTrackingDTOList(List<InfectionTracking> infectionTrackingList){

        List<InfectionTrackingDTO> infectionTrackingDTOList = new ArrayList<>();
        for (InfectionTracking infectionTracking : infectionTrackingList){
            infectionTrackingDTOList.add(
                    makeInfectionTrackingDTO(infectionTracking)
            );
        }
        return infectionTrackingDTOList;
    }

    public InfectionTracking makeInfectionTracking(Patient patient, InfectionTrackingDTO infectionTrackingDTO){

        return InfectionTracking
                .builder()
                .infectionTrackingArea(infectionTrackingDTO.getInfectionTrackingArea())
                .infectionTrackingCause(infectionTrackingDTO.getInfectionTrackingCause())
                .infectionTrackingDate(infectionTrackingDTO.getInfectionTrackingDate())
                .infectionTrackingId(infectionTrackingDTO.getInfectionTrackingId())
                .patient(patient)
                .build();
    }
}
