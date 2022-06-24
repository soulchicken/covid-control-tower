package com.dev.covid.controller;

import com.dev.covid.DTO.HospitalDTO;
import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.DTO.SelfQuarantineDTO;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.Patient;
import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private Service service;
    /**
     * 전체 환자를 조회를 한다.
     */
    @GetMapping
    public List<PatientDTO> findAll() {
        log.info("에러 발생입니다.");
        List<Patient> patientList = service.findAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList) {
            SelfQuarantine selfQuarantine = patient.getSelfQuarantine();
            Hospital hospital = patient.getHospital();
            HospitalDTO hospitalDTO;
            SelfQuarantineDTO selfQuarantineDTO;
            if (selfQuarantine == null || hospital == null) {
                selfQuarantineDTO = null;
                hospital = null;
            } else {
                selfQuarantineDTO = SelfQuarantineDTO
                        .builder()
                        .selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
                        .selfQuarantineId(selfQuarantine.getSelfQuarantineId())
                        .selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
                        .patientName(patient.getPeopleName())
                        .build();
                hospitalDTO = HospitalDTO
                        .builder()
                        .hospitalRoomlimit(hospital.getHospitalRoomlimit())
                        .hospitalPatientnum(hospital.getHospitalPatientnum())
                        .hospitalRoom(hospital.getHospitalRoom())
                        .hospitalName(hospital.getHospitalName())
                        .hospitalId(hospital.getHospitalId())
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
        return patientDTOList;
    }


    @GetMapping("/id/{patientId}")
    public PatientDTO findById(@PathVariable("patientId") Long id) {
        Patient newPatient = service.findById(id);

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
                .build();
        return patientDTO;


    }

    @PostMapping
    public PatientDTO save(@RequestBody PatientDTO newPatientDTO) {
        Patient newPatient = service.save(newPatientDTO);
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
                .peopleId(newPatient.getPeopleId())
                .managerId(newPatient.getManager().getManagerId())
                .peopleName(newPatient.getPeopleName())
                .peoplePhone(newPatient.getPeoplePhone())
                .selfQuarantineDTO(selfQuarantineDTO)
                .build();
        return patientDTO;
    }

    // putMapping

    /**
     * 할 일을 정보를 갱신한다.
     */
    @PutMapping
    public PatientDTO update(@RequestBody Patient patient) {

        Patient newPatient = service.update(patient);
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
                .build();
        return patientDTO;
    }

    @DeleteMapping("/{patientId}")
    public List<PatientDTO> delete(@PathVariable("patientId") Long id) {

        List<Patient> patientList = service.delete(id);
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
                    .build();
            patientDTOList.add(patientDTO);
        }
        return patientDTOList;
    }

}
