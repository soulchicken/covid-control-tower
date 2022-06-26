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
        List<InfectionTrackingDTO> infectionTrackingDTOList = infectionTrackingService.makeInfectionTrackingDTOList(infectionTrackingList);
        return ResponseEntity.ok(infectionTrackingDTOList);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody InfectionTrackingDTO infectionTrackingDTO) {
        try {
            InfectionTracking infectionTracking = infectionTrackingService.save(infectionTrackingDTO);
            InfectionTrackingDTO newInfectionTrackingDTO = infectionTrackingService.makeInfectionTrackingDTO(infectionTracking);
            return ResponseEntity.ok(newInfectionTrackingDTO);
        } catch (Exception e) {
            log.error("Not found Patient ID {} " + e.getMessage(), infectionTrackingDTO.getPatientPeopleId());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody InfectionTrackingDTO infectionTrackingDTO) {
        try {
            InfectionTracking infectionTracking = infectionTrackingService.update(infectionTrackingDTO);
            InfectionTrackingDTO newInfectionTrackingDTO = infectionTrackingService.makeInfectionTrackingDTO(infectionTracking);
            return ResponseEntity.ok(newInfectionTrackingDTO);

        } catch (Exception e){
            log.error("Not found infectionTracking ID {} " + e.getMessage(), infectionTrackingDTO.getInfectionTrackingId());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            List<InfectionTracking> infectionTrackingList = infectionTrackingService.delete(id);
            List<InfectionTrackingDTO> infectionTrackingDTOList = infectionTrackingService.makeInfectionTrackingDTOList(infectionTrackingList);
            return ResponseEntity.ok(infectionTrackingDTOList);
        } catch (Exception e){
            log.error("Not found Patient ID {} " + e.getMessage(), id);
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }

    }

}
