package com.dev.covid.controller;

import com.dev.covid.DTO.DangerDTO;
import com.dev.covid.model.Danger;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.model.InfectionTracking;
import com.dev.covid.service.DangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("danger")
public class DangerController {

    @Autowired
    private DangerService dangerService;

    @GetMapping
    public List<DangerDTO> findAll() {
        List<Danger> dangerList = dangerService.findAll();
        List<DangerDTO> dangerDTOList = new ArrayList<>();
        for (Danger danger : dangerList) {
            dangerDTOList.add(DangerDTO
                    .builder()
                    .dangerId(danger.getDangerId())
                    .dangerCareDate(danger.getDangerCareDate())
                    .dangerCareRelease(danger.getDangerCareRelease())
                    .hospitalRoomnumber(danger.getHospitalRoomnumber())
                    .patientId(danger.getPatient().getPeopleId())
                    .build()
            );
        }
        return dangerDTOList;
    }

    @PostMapping
    public DangerDTO save(@RequestBody DangerDTO dangerDTO) {
        Danger newDanger = dangerService.save(dangerDTO);
        DangerDTO newDangerDTO = DangerDTO
                .builder()
                .patientId(newDanger.getPatient().getPeopleId())
                .dangerId(newDanger.getDangerId())
                .dangerCareDate(newDanger.getDangerCareRelease())
                .dangerCareRelease(newDanger.getDangerCareRelease())
                .hospitalRoomnumber(newDanger.getHospitalRoomnumber())
                .build();
        return newDangerDTO;
    }

    @PutMapping
    public List<DangerDTO> update(@RequestBody Danger updateDanger) {
        List<Danger> dangerList = dangerService.update(updateDanger);
        List<DangerDTO> dangerDTOList = new ArrayList<>();
        for (Danger danger : dangerList) {
            dangerDTOList.add(DangerDTO
                    .builder()
                    .dangerId(danger.getDangerId())
                    .dangerCareDate(danger.getDangerCareDate())
                    .dangerCareRelease(danger.getDangerCareRelease())
                    .hospitalRoomnumber(danger.getHospitalRoomnumber())
                    .patientId(danger.getPatient().getPeopleId())
                    .build()
            );
        }
        return dangerDTOList;
    }

    @DeleteMapping("/{id}")
    public List<DangerDTO> delete(@PathVariable("id") Long id) {
        List<Danger> dangerList = dangerService.delete(id);
        List<DangerDTO> dangerDTOList = new ArrayList<>();
        for (Danger danger : dangerList) {
            dangerDTOList.add(DangerDTO
                    .builder()
                    .dangerId(danger.getDangerId())
                    .dangerCareDate(danger.getDangerCareDate())
                    .dangerCareRelease(danger.getDangerCareRelease())
                    .hospitalRoomnumber(danger.getHospitalRoomnumber())
                    .patientId(danger.getPatient().getPeopleId())
                    .build()
            );
        }
        return dangerDTOList;
    }
}
