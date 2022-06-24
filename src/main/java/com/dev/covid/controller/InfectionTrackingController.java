package com.dev.covid.controller;

import com.dev.covid.DTO.InfectionTrackingDTO;
import com.dev.covid.DTO.ResponseDTO;
import com.dev.covid.model.InfectionTracking;
import com.dev.covid.model.Patient;
import com.dev.covid.service.InfectionTrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("infectiontracking")
public class InfectionTrackingController {
    @Autowired
    private InfectionTrackingService infectionTrackingService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<InfectionTracking> infectionTrackingList = infectionTrackingService.findAll();
        List<InfectionTrackingDTO> infectionTrackingDTOList = new ArrayList<>();
        for (InfectionTracking infectionTracking : infectionTrackingList){
            infectionTrackingDTOList.add(
                    InfectionTrackingDTO
                            .builder()
                            .infectionTrackingId(infectionTracking.getInfectionTrackingId())
                            .patientPeopleId(infectionTracking.getPatient().getPeopleId())
                            .infectionTrackingName(infectionTracking.getInfectionTrackingName())
                            .infectionTrackingDate(infectionTracking.getInfectionTrackingDate())
                            .infectionTrackingCause(infectionTracking.getInfectionTrackingCause())
                            .infectionTrackingArea(infectionTracking.getInfectionTrackingArea())
                            .build()
            );
        }
        return ResponseEntity.ok(infectionTrackingDTOList);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody InfectionTrackingDTO infectionTrackingDTO) {
        try {
            InfectionTracking infectionTracking = infectionTrackingService.save(infectionTrackingDTO);
            InfectionTrackingDTO newInfectionTrackingDTO = InfectionTrackingDTO
                    .builder()
                    .infectionTrackingArea(infectionTracking.getInfectionTrackingArea())
                    .infectionTrackingCause(infectionTracking.getInfectionTrackingCause())
                    .infectionTrackingDate(infectionTracking.getInfectionTrackingDate())
                    .infectionTrackingId(infectionTracking.getInfectionTrackingId())
                    .infectionTrackingName(infectionTracking.getInfectionTrackingName())
                    .patientPeopleId(infectionTracking.getPatient().getPeopleId())
                    .build();
            return ResponseEntity.ok(newInfectionTrackingDTO);
        } catch (Exception e) {
            log.error("Not found Patient ID {} " + e.getMessage(), infectionTrackingDTO.getPatientPeopleId());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }

    }

    @PutMapping
    public List<InfectionTracking> update(@RequestBody InfectionTracking infectionTracking) {
        return infectionTrackingService.update(infectionTracking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            List<InfectionTracking> infectionTrackingList = infectionTrackingService.delete(id);
            List<InfectionTrackingDTO> infectionTrackingDTOList = new ArrayList<>();
            for (InfectionTracking infectionTracking : infectionTrackingList){
                infectionTrackingDTOList.add(
                        InfectionTrackingDTO
                                .builder()
                                .infectionTrackingId(infectionTracking.getInfectionTrackingId())
                                .patientPeopleId(infectionTracking.getPatient().getPeopleId())
                                .infectionTrackingName(infectionTracking.getInfectionTrackingName())
                                .infectionTrackingDate(infectionTracking.getInfectionTrackingDate())
                                .infectionTrackingCause(infectionTracking.getInfectionTrackingCause())
                                .infectionTrackingArea(infectionTracking.getInfectionTrackingArea())
                                .build()
                );
            }
            return ResponseEntity.ok(infectionTrackingDTOList);
        } catch (Exception e){
            log.error("Not found Patient ID {} " + e.getMessage(), id);
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }

    }

}
