package com.dev.covid.controller;


import com.dev.covid.DTO.HospitalDTO;
import com.dev.covid.DTO.ResponseDTO;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("hospital")
@Slf4j
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // ์กฐํ
    @GetMapping

    public ResponseEntity<?> findAll() {
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
                            .hospitalPatientnum(hospital.getHospitalPatientnum())
                            .hospitalRoomlimit(hospital.getHospitalRoomlimit())
                            .hospitalRoomNumberList(hospitalRoomNumberList)
                            .build()
            );
        }
        return ResponseEntity.ok(hospitalDTOList);

    }

    // ์ฝ์
    @PostMapping
    public ResponseEntity<?> save(@RequestBody HospitalDTO hospitalDTO) {
        try {

            Hospital newHospital = hospitalService.save(hospitalDTO);
            HospitalDTO newHospitalDTO = hospitalService.hospitalDTO(newHospital);
            return ResponseEntity.ok(newHospitalDTO);
        } catch (Exception e) {
            log.error("๋ณ์์?๋ณด์ ๋ฑ๋ก์ ์คํจํ์ต๋๋ค." + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // ์์?
    @PutMapping
    public ResponseEntity<?> update(@RequestBody HospitalDTO updateHospitalDTO) {
        try {
            Hospital newHospital = hospitalService.update(updateHospitalDTO);
                List<Long> hospitalRoomNumberList = new ArrayList<>();
                for (HospitalRoom hospitalRoom : newHospital.getHospitalRoomList()) {
                    hospitalRoomNumberList.add(hospitalRoom.getHospitalroomRoomnumber());
                }
                HospitalDTO hospitalDTO = hospitalService.hospitalDTO(newHospital);
            return ResponseEntity.ok(hospitalDTO);
        } catch (Exception e) {
            log.error("๋ณ์์?๋ณด์ ์๋ฐ์ดํธ์ ์คํจํ์ต๋๋ค." + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }



    }

    // ์ญ์?
    @DeleteMapping("/{id}")

    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        try {
    
            Hospital newHospital = hospitalService.delete(id);


            List<Long> hospitalRoomNumberList = new ArrayList<>();
            for (HospitalRoom hospitalRoom : newHospital.getHospitalRoomList()) {
                hospitalRoomNumberList.add(hospitalRoom.getHospitalroomRoomnumber());
            }

            HospitalDTO hospitalDTO = hospitalService.hospitalDTO(newHospital);
            return ResponseEntity.ok(hospitalDTO);
        } catch (Exception e) {
            log.error("๋ณ์์?๋ณด์ ์ญ์?์ ์คํจํ์ต๋๋ค." + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }
}