package com.dev.covid.controller;


import com.dev.covid.DTO.HospitalRoomDTO;
import com.dev.covid.DTO.PatientDTO;
import com.dev.covid.DTO.ResponseDTO;
import com.dev.covid.DTO.SelfQuarantineDTO;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.model.Patient;
import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.HospitalRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("hospitalroom")
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService hospitalroomService;

    // 조회
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<HospitalRoom> hospitalRoomList = hospitalroomService.findAll();
        if (hospitalRoomList.size() == 0) {
            log.error("병실이 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당되는 날짜가 없습니다.").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
        List<HospitalRoomDTO> hospitalRoomDTOList = hospitalroomService.hospitalRoomToDTOList(hospitalRoomList);
        return ResponseEntity.ok(hospitalRoomDTOList);
    }

    // 삽입
    @PostMapping
    public ResponseEntity<?> save(@RequestBody HospitalRoomDTO hospitalRoomDTO) {
        try {
            HospitalRoom newHospitalRoom = hospitalroomService.save(hospitalRoomDTO);
            HospitalRoomDTO newHospitalRoomDTO = hospitalroomService.hospitalRoomToDTO(newHospitalRoom);
            return ResponseEntity.ok(newHospitalRoomDTO);
        } catch (Exception e) {
            log.error("병실 정보 저장에 실패했습니다 : " + e.getStackTrace());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 수정
    @PutMapping
    public ResponseEntity<?> update(@RequestBody HospitalRoomDTO putHospitalRoom) {
        try {
            HospitalRoom hospitalRoom = hospitalroomService.update(putHospitalRoom);
            HospitalRoomDTO hospitalRoomDTO = hospitalroomService.hospitalRoomToDTO(hospitalRoom);
            return ResponseEntity.ok(hospitalRoomDTO);
        } catch (Exception e) {
            log.error("병실 정보 변경에 실패햇습니다. : " + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
        try {
            HospitalRoom hospitalRoomList = hospitalroomService.delete(id);
            HospitalRoomDTO hospitalRoomDTO = hospitalroomService.hospitalRoomToDTO(hospitalRoomList);
            return ResponseEntity.ok(hospitalRoomDTO);
        } catch (Exception e) {
            log.error("병실 정보 삭제에 실패했습니다. : " + e.getStackTrace());
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
