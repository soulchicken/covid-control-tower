package com.dev.covid.controller;


import com.dev.covid.DTO.HospitalRoomDTO;
import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.DTO.SelfQuarantineDTO;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.model.Patient;
import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("hospitalroom")
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService hospitalroomService;

    // 조회
    @GetMapping
    public List<HospitalRoomDTO> findAll() {
        List<HospitalRoom> hospitalRoomList = hospitalroomService.findAll();
        List<HospitalRoomDTO> hospitalRoomDTOList = new ArrayList<>();
        for (HospitalRoom hospitalRoom : hospitalRoomList){

            HospitalRoomDTO hospitalRoomDTO = HospitalRoomDTO
                    .builder()
                    .hospitalroomCapacity(hospitalRoom.getHospitalroomCapacity())
                    .hospitalroomRoomnumber(hospitalRoom.getHospitalroomRoomnumber())
                    .build();
            hospitalRoomDTOList.add(hospitalRoomDTO);
        }
        return hospitalRoomDTOList;
    }

    // 삽입
    @PostMapping
    public HospitalRoom save(@RequestBody HospitalRoom hospitalroom) {
        return hospitalroomService.save(hospitalroom);
    }

    // 수정
    @PutMapping
    public List<HospitalRoomDTO> update(@RequestBody HospitalRoom hospitalroom) {
        List<HospitalRoom> hospitalRoomList = hospitalroomService.update(hospitalroom);
        List<HospitalRoomDTO> hospitalRoomDTOList = new ArrayList<>();
        for (HospitalRoom hospitalRoom : hospitalRoomList){

            HospitalRoomDTO hospitalRoomDTO = HospitalRoomDTO
                    .builder()
                    .hospitalroomCapacity(hospitalRoom.getHospitalroomCapacity())
                    .hospitalroomRoomnumber(hospitalRoom.getHospitalroomRoomnumber())
                    .build();
            hospitalRoomDTOList.add(hospitalRoomDTO);
        }
        return hospitalRoomDTOList;
    }

    // 삭제
    @DeleteMapping("/{id}")
    public List<HospitalRoomDTO> delete(@PathVariable("id") Long id) {
        List<HospitalRoom> hospitalRoomList = hospitalroomService.delete(id);
        List<HospitalRoomDTO> hospitalRoomDTOList = new ArrayList<>();
        for (HospitalRoom hospitalRoom : hospitalRoomList){

            HospitalRoomDTO hospitalRoomDTO = HospitalRoomDTO
                    .builder()
                    .hospitalroomCapacity(hospitalRoom.getHospitalroomCapacity())
                    .hospitalroomRoomnumber(hospitalRoom.getHospitalroomRoomnumber())
                    .build();
            hospitalRoomDTOList.add(hospitalRoomDTO);
        }
        return hospitalRoomDTOList;
    }
}
