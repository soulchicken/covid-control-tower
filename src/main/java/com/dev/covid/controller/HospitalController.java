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

    // 조회
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

    // 삽입
    @PostMapping
    public ResponseEntity<?> save(@RequestBody HospitalDTO hospitalDTO) {
        try {
            Hospital newHospital = hospitalService.save(hospitalDTO);
            HospitalDTO newHospitalDTO = hospitalService.hospitalDTO(newHospital);
            return ResponseEntity.ok(newHospitalDTO);
        } catch (Exception e) {
            log.error("병원정보의 등록에 실패했습니다." + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 수정
    @PutMapping

    public ResponseEntity<?> update(@RequestBody HospitalDTO updatehospital) {
        try {
            Hospital newHospital = hospitalService.update(updatehospital);
                List<Long> hospitalRoomNumberList = new ArrayList<>();
                for (HospitalRoom hospitalRoom : newHospital.getHospitalRoomList()) {
                    hospitalRoomNumberList.add(hospitalRoom.getHospitalroomRoomnumber());
                }
                HospitalDTO hospitalDTO = hospitalService.hospitalDTO(newHospital);
            return ResponseEntity.ok(hospitalDTO);
        } catch (Exception e) {
            log.error("병원정보의 업데이트에 실패했습니다." + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }


    }

    // 삭제
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
            log.error("병원정보의 삭제에 실패했습니다." + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }
}
