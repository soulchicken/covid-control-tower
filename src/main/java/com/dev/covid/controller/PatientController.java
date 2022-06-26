package com.dev.covid.controller;

import com.dev.covid.DTO.*;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.Manager;
import com.dev.covid.model.Patient;
import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.PatientService;
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
            ManagerDTO managerDTO;
            HospitalDTO hospitalDTO;
            SelfQuarantineDTO selfQuarantineDTO;
            if (selfQuarantine == null || hospital == null) {
                selfQuarantineDTO = null;
                hospitalDTO = null;
                managerDTO = null;
            } else {
                selfQuarantineDTO = SelfQuarantineDTO
                        .builder()
                        .selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
                        .selfQuarantineId(selfQuarantine.getSelfQuarantineId())
                        .selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
                        .patientName(patient.getPeopleName())
                        .build();
            }

            PatientDTO patientDTO = PatientDTO
                    .builder()
                    .peopleName(patient.getPeopleName())
                    .peopleAge(patient.getPeopleAge())
                    .peopleHome(patient.getPeopleHome())
                    .managerId(patient.getManager().getManagerId())
                    .peopleId(patient.getPeopleId())
                    .peopleGender(patient.getPeopleGender())
                    .peoplePhone(patient.getPeoplePhone())
                    .selfQuarantineDTO(selfQuarantineDTO)
                    .hospitalId(patient.getHospital().getHospitalId())
                    .build();
            patientDTOList.add(patientDTO);
        }
        return ResponseEntity.ok(patientDTOList);
    }


    @GetMapping("/id/{patientId}")
    public PatientDTO findById(@PathVariable("patientId") Long id) {
        Patient newPatient = patientService.findById(id);

        SelfQuarantine selfQuarantine = newPatient.getSelfQuarantine();
        if (selfQuarantine == null) {
            PatientDTO patientDTO = PatientDTO
                    .builder()
                    .peopleAge(newPatient.getPeopleAge())
                    .peopleGender(newPatient.getPeopleGender())
                    .peopleHome(newPatient.getPeopleHome())
                    .managerId(newPatient.getManager().getManagerId())
                    .peopleId(newPatient.getPeopleId())
                    .peopleName(newPatient.getPeopleName())
                    .peoplePhone(newPatient.getPeoplePhone())
                    .hospitalId(newPatient.getHospital().getHospitalId())
                    .build();
            return patientDTO;
        }

        SelfQuarantineDTO selfQuarantineDTO = SelfQuarantineDTO
                .builder()
                .selfQuarantineId(selfQuarantine.getSelfQuarantineId())
                .patientName(newPatient.getPeopleName())
                .selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
                .selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
                .build();

        PatientDTO patientDTO = PatientDTO
                .builder()
                .peopleAge(newPatient.getPeopleAge())
                .peopleGender(newPatient.getPeopleGender())
                .peopleHome(newPatient.getPeopleHome())
                .managerId(newPatient.getManager().getManagerId())
                .peopleId(newPatient.getPeopleId())
                .peopleName(newPatient.getPeopleName())
                .peoplePhone(newPatient.getPeoplePhone())
                .selfQuarantineDTO(selfQuarantineDTO)
                .hospitalId(newPatient.getHospital().getHospitalId())
                .build();
        return patientDTO;


    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PatientDTO newPatientDTO) {
        try{
            Patient newPatient = patientService.save(newPatientDTO);
            SelfQuarantine selfQuarantine = newPatient.getSelfQuarantine();
            if (selfQuarantine == null) {
                PatientDTO patientDTO = PatientDTO
                        .builder()
                        .peopleAge(newPatient.getPeopleAge())
                        .managerId(newPatient.getManager().getManagerId())
                        .peopleGender(newPatient.getPeopleGender())
                        .peopleHome(newPatient.getPeopleHome())
                        .peopleId(newPatient.getPeopleId())
                        .peopleName(newPatient.getPeopleName())
                        .managerId(newPatient.getManager().getManagerId())
                        .peoplePhone(newPatient.getPeoplePhone())
                        .hospitalId(newPatient.getHospital().getHospitalId())
                        .build();
                return ResponseEntity.ok(patientDTO);

            }

            SelfQuarantineDTO selfQuarantineDTO = SelfQuarantineDTO
                    .builder()
                    .selfQuarantineId(selfQuarantine.getSelfQuarantineId())
                    .patientName(newPatient.getPeopleName())
                    .selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
                    .selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
                    .build();

            PatientDTO patientDTO = PatientDTO
                    .builder()
                    .peopleAge(newPatient.getPeopleAge())
                    .peopleGender(newPatient.getPeopleGender())
                    .peopleHome(newPatient.getPeopleHome())
                    .peopleId(newPatient.getPeopleId())
                    .managerId(newPatient.getManager().getManagerId())
                    .peopleName(newPatient.getPeopleName())
                    .peoplePhone(newPatient.getPeoplePhone())
                    .selfQuarantineDTO(selfQuarantineDTO)
                    .hospitalId(newPatient.getHospital().getHospitalId())
                    .build();
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
            PatientDTO patientDTO = PatientDTO
                    .builder()
                    .peopleAge(newPatient.getPeopleAge())
                    .peopleGender(newPatient.getPeopleGender())
                    .peopleHome(newPatient.getPeopleHome())
                    .peopleId(newPatient.getPeopleId())
                    .peopleName(newPatient.getPeopleName())
                    .managerId(newPatient.getManager().getManagerId())
                    .peoplePhone(newPatient.getPeoplePhone())
                    .hospitalId(newPatient.getHospital().getHospitalId())
                    .build();
            return patientDTO;
        }

        SelfQuarantineDTO selfQuarantineDTO = SelfQuarantineDTO
                .builder()
                .selfQuarantineId(selfQuarantine.getSelfQuarantineId())
                .patientName(newPatient.getPeopleName())
                .selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
                .selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
                .build();

        PatientDTO patientDTO = PatientDTO
                .builder()
                .peopleAge(newPatient.getPeopleAge())
                .peopleGender(newPatient.getPeopleGender())
                .peopleHome(newPatient.getPeopleHome())
                .managerId(newPatient.getManager().getManagerId())
                .peopleId(newPatient.getPeopleId())
                .peopleName(newPatient.getPeopleName())
                .peoplePhone(newPatient.getPeoplePhone())
                .selfQuarantineDTO(selfQuarantineDTO)
                .hospitalId(newPatient.getHospital().getHospitalId())
                .build();
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
                    selfQuarantineDTO = SelfQuarantineDTO
                            .builder()
                            .selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
                            .selfQuarantineId(selfQuarantine.getSelfQuarantineId())
                            .selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
                            .patientName(patient.getPeopleName())
                            .build();
                }

                PatientDTO patientDTO = PatientDTO
                        .builder()
                        .peopleName(patient.getPeopleName())
                        .peopleAge(patient.getPeopleAge())
                        .peopleHome(patient.getPeopleHome())
                        .peopleId(patient.getPeopleId())
                        .peopleGender(patient.getPeopleGender())
                        .peoplePhone(patient.getPeoplePhone())
                        .selfQuarantineDTO(selfQuarantineDTO)
                        .hospitalId(patient.getHospital().getHospitalId())
                        .build();
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