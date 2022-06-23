package com.dev.covid.controller;


import com.dev.covid.DTO.HospitalDTO;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.model.Patient;

import com.dev.covid.model.Hospital;
import com.dev.covid.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // 조회
    @GetMapping

    public List<HospitalDTO> findAll() {
        List<Hospital> hospitalList = hospitalService.findAll();
        List<HospitalDTO> hospitalDTOList = new ArrayList<>();
        for (Hospital hospital : hospitalList) {
            List<Long> hospitalRoomNumberList = new ArrayList<>();
            for (HospitalRoom hospitalRoom : hospital.getHospitalRoomList()) {
                hospitalRoomNumberList.add(hospitalRoom.getHospitalroomRoomnumber());
            }
            hospitalDTOList.add(
                    HospitalDTO
                            .builder()
                            .hospitalId(hospital.getHospitalId())
                            .hospitalName(hospital.getHospitalName())
                            .hospitalRoom(hospital.getHospitalRoom())
                            .hospitalPatientnum(hospital.getHospitalPatientnum())
                            .hospitalRoomlimit(hospital.getHospitalRoomlimit())
                            .hospitalRoomNumberList(hospitalRoomNumberList)
                            .build()
            );
        }
        return hospitalDTOList;


    }

    // 삽입
    @PostMapping
    public Hospital save(@RequestBody Hospital hospital) {
        return hospitalService.save(hospital);
    }

    // 수정
    @PutMapping

    public List<HospitalDTO> update(@RequestBody Hospital Updatehospital) {

        List<Hospital> hospitalList = hospitalService.update(Updatehospital);
        List<HospitalDTO> hospitalDTOList = new ArrayList<>();
        for (Hospital hospital : hospitalList) {
            List<Long> hospitalRoomNumberList = new ArrayList<>();
            for (HospitalRoom hospitalRoom : hospital.getHospitalRoomList()) {
                hospitalRoomNumberList.add(hospitalRoom.getHospitalroomRoomnumber());
            }
            hospitalDTOList.add(
                    HospitalDTO
                            .builder()
                            .hospitalId(hospital.getHospitalId())
                            .hospitalName(hospital.getHospitalName())
                            .hospitalRoom(hospital.getHospitalRoom())
                            .hospitalPatientnum(hospital.getHospitalPatientnum())
                            .hospitalRoomlimit(hospital.getHospitalRoomlimit())
                            .hospitalRoomNumberList(hospitalRoomNumberList)
                            .build()
            );
        }
        return hospitalDTOList;


    }

    // 삭제
    @DeleteMapping("/{id}")

    public List<HospitalDTO> delete(@PathVariable("id") Long id) {
        List<Hospital> hospitalList = hospitalService.delete(id);
        List<HospitalDTO> hospitalDTOList = new ArrayList<>();
        for (Hospital hospital : hospitalList) {
            List<Long> hospitalRoomNumberList = new ArrayList<>();
            for (HospitalRoom hospitalRoom : hospital.getHospitalRoomList()) {
                hospitalRoomNumberList.add(hospitalRoom.getHospitalroomRoomnumber());
            }
            hospitalDTOList.add(
                    HospitalDTO
                            .builder()
                            .hospitalId(hospital.getHospitalId())
                            .hospitalName(hospital.getHospitalName())
                            .hospitalRoom(hospital.getHospitalRoom())
                            .hospitalPatientnum(hospital.getHospitalPatientnum())
                            .hospitalRoomlimit(hospital.getHospitalRoomlimit())
                            .hospitalRoomNumberList(hospitalRoomNumberList)
                            .build()
            );
        }
        return hospitalDTOList;


    }
}
