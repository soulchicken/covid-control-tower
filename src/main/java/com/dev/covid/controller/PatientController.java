package com.dev.covid.controller;

import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.DTO.SelfQuarantineDTO;
import com.dev.covid.model.Patient;
import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private Service service;

    /**
     * 전체 환자를 조회를 한다.
     */
    @GetMapping
    public List<PatientDTO> findAll() {
        List<Patient> patientList = service.findAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList){
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


    @GetMapping("/id/{patientId}")
    public PatientDTO findById(@PathVariable("patientId") Long id) {
        System.out.println(id);
        Patient newPatient = service.findById(id);

        SelfQuarantine selfQuarantine = newPatient.getSelfQuarantine();
        if (selfQuarantine == null) {
            PatientDTO patientDTO = PatientDTO
                    .builder()
                    .peopleAge(newPatient.getPeopleAge())
                    .peopleGender(newPatient.getPeopleGender())
                    .peopleHome(newPatient.getPeopleHome())
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
                .peopleId(newPatient.getPeopleId())
                .peopleName(newPatient.getPeopleName())
                .peoplePhone(newPatient.getPeoplePhone())
                .selfQuarantineDTO(selfQuarantineDTO)
                .build();
        return patientDTO;


    }

    @PostMapping
    public PatientDTO save(@RequestBody Patient patient) {
        // @RequestBody - 클라이언트에서 보낸 값을 Todo의 필드와 맵핑해서 객체 형태로 바인딩
        System.out.println(patient);

        Patient newPatient = service.save(patient);
        SelfQuarantine selfQuarantine = newPatient.getSelfQuarantine();
        if (selfQuarantine == null) {
            PatientDTO patientDTO = PatientDTO
                    .builder()
                    .peopleAge(newPatient.getPeopleAge())
                    .peopleGender(newPatient.getPeopleGender())
                    .peopleHome(newPatient.getPeopleHome())
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
                .peopleId(newPatient.getPeopleId())
                .peopleName(newPatient.getPeopleName())
                .peoplePhone(newPatient.getPeoplePhone())
                .selfQuarantineDTO(selfQuarantineDTO)
                .build();
        return patientDTO;
    }

    // putMapping
    /**
     * 할 일을 정보를 갱신한다.
     *
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
                .peopleName(newPatient.getPeopleName())
                .peoplePhone(newPatient.getPeoplePhone())
                .selfQuarantineDTO(selfQuarantineDTO)
                .build();
        return patientDTO;
    }

    /**
     *

     */
    @DeleteMapping("/{patientId}")
    public List<PatientDTO> delete(@PathVariable("patientId") Long id) {

        List<Patient> patientList = service.delete(id);
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient : patientList){
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
