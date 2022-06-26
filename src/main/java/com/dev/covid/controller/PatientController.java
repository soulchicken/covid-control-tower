package com.dev.covid.controller;

import com.dev.covid.DTO.*;
import com.dev.covid.model.*;
import com.dev.covid.service.InfectionTrackingService;
import com.dev.covid.service.PatientService;
import com.dev.covid.service.SelfQuarantineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private SelfQuarantineService selfQuarantineService;

    @Autowired
    private InfectionTrackingService infectionTrackingService;
    /**
     * 전체 환자를 조회를 한다.
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Patient> patientList = patientService.findAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            SelfQuarantine selfQuarantine = patient.getSelfQuarantine();
            Hospital hospital = patient.getHospital();
            Manager manager = patient.getManager();
            List<InfectionTracking> infectionTrackingList = patient.getInfectionTrackingList();
            List<InfectionTrackingDTO> infectionTrackingDTOList = new ArrayList<>();
            for (InfectionTracking infectionTracking : infectionTrackingList){
                infectionTrackingDTOList.add(
                        infectionTrackingService.makeInfectionTrackingDTO(infectionTracking)
                );
            }
            ManagerDTO managerDTO;
            HospitalDTO hospitalDTO;
            SelfQuarantineDTO selfQuarantineDTO;
            if (selfQuarantine == null || hospital == null) {
                selfQuarantineDTO = null;
                hospitalDTO = null;
            } else {
                selfQuarantineDTO = selfQuarantineService.selfQuarantineToDTO(selfQuarantine);

            }
            PatientDTO patientDTO = patientService.makePatientDTO(patient,selfQuarantineDTO,infectionTrackingDTOList);

            patientDTOList.add(patientDTO);
        }
        return ResponseEntity.ok(patientDTOList);
    }


    @GetMapping("/id/{patientId}")
    public ResponseEntity<?> findById(@PathVariable("patientId") Long id) {
        try {
            Patient newPatient = patientService.findById(id);

            List<InfectionTracking> infectionTrackingList = newPatient.getInfectionTrackingList();

            List<InfectionTrackingDTO> infectionTrackingDTOList = new ArrayList<>();

            for (InfectionTracking infectionTracking : infectionTrackingList){
                infectionTrackingDTOList.add(
                        infectionTrackingService.makeInfectionTrackingDTO(infectionTracking)
                );
            }

            SelfQuarantine selfQuarantine = newPatient.getSelfQuarantine();
            if (selfQuarantine == null) {

                PatientDTO patientDTO = patientService.makePatientDTO(newPatient,infectionTrackingDTOList);
                return  ResponseEntity.ok(patientDTO);
            }

            SelfQuarantineDTO selfQuarantineDTO = selfQuarantineService.selfQuarantineToDTO(selfQuarantine);

            PatientDTO patientDTO = patientService.makePatientDTO(newPatient,selfQuarantineDTO,infectionTrackingDTOList);


            return  ResponseEntity.ok(patientDTO);

        } catch (Exception e) {
            log.error("Not found Patient ID {} " + e.getMessage(), id);
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }


    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PatientDTO newPatientDTO) {
        try{
            Patient newPatient = patientService.save(newPatientDTO);
            SelfQuarantine selfQuarantine = newPatient.getSelfQuarantine();
            if (selfQuarantine == null) {
                PatientDTO patientDTO = patientService.makePatientDTO(newPatient);
                return ResponseEntity.ok(patientDTO);

            }

            SelfQuarantineDTO selfQuarantineDTO = selfQuarantineService.selfQuarantineToDTO(selfQuarantine);

            PatientDTO patientDTO = patientService.makePatientDTO(newPatient,selfQuarantineDTO);

            return ResponseEntity.ok(patientDTO);
        } catch (Exception e) {
            log.error("Not found Patient ID {}" + e.getMessage(), newPatientDTO.getPeopleId());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // putMapping

    /**
     * 할 일을 정보를 갱신한다.
     */
    @PutMapping
    public PatientDTO update(@RequestBody Patient patient) {

        Patient newPatient = patientService.update(patient);
        SelfQuarantine selfQuarantine = newPatient.getSelfQuarantine();
        if (selfQuarantine == null) {
            PatientDTO patientDTO = patientService.makePatientDTO(newPatient);
            return patientDTO;
        }

        SelfQuarantineDTO selfQuarantineDTO = selfQuarantineService.selfQuarantineToDTO(selfQuarantine);
        PatientDTO patientDTO = patientService.makePatientDTO(newPatient,selfQuarantineDTO);
        return patientDTO;
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> delete(@PathVariable("patientId") Long id) {
        try {
            List<Patient> patientList = patientService.delete(id);
            List<PatientDTO> patientDTOList = new ArrayList<>();
            for (Patient patient : patientList) {
                SelfQuarantine selfQuarantine = patient.getSelfQuarantine();
                SelfQuarantineDTO selfQuarantineDTO;
                if (selfQuarantine == null) {
                    selfQuarantineDTO = null;
                } else {
                    selfQuarantineDTO = selfQuarantineService.selfQuarantineToDTO(selfQuarantine);
                }
                PatientDTO patientDTO = patientService.makePatientDTO(patient,selfQuarantineDTO);
                patientDTOList.add(patientDTO);
            }
            return ResponseEntity.ok(patientDTOList);
        } catch (Exception e){
            log.error("Not found Patient ID {} " + e.getMessage(), id);
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

}