package com.dev.covid.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dev.covid.DTO.ManagerDTO;
import com.dev.covid.DTO.ResponseDTO;
import com.dev.covid.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.covid.model.Manager;
import com.dev.covid.service.ManagerService;

@Slf4j
@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Manager> managerList = managerService.findAll();
        if (managerList.size() == 0) {
            log.error("환자관리자가 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당되는 사람이 없습니다.").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
        List<ManagerDTO> managerDTOList = managerService.managerToDTOList(managerList);
        return ResponseEntity.ok(managerDTOList);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ManagerDTO managerDTO) {
        try {
            Manager newManager = managerService.save(managerDTO);
            ManagerDTO newManagerDTO = managerService.managerToDTO(newManager);
            return ResponseEntity.ok(newManagerDTO);
        } catch (Exception e) {
            log.error("환자관리자 정보 저장에 실패했습니다 : " + e.getStackTrace());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody ManagerDTO putManager) {
        try {
            Manager manager = managerService.update(putManager);
            List<String> patientNameList = new ArrayList<>();
            for (Patient patient : manager.getPatientList()){
                patientNameList.add(patient.getPeopleName());
            }
            ManagerDTO managerDTO = managerService.managerToDTO(manager,patientNameList);
            return ResponseEntity.ok(managerDTO);
        } catch (Exception e) {
            log.error("환자관리자 정보 변경에 실패했습니다. : " + e.getStackTrace());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Manager managerList = managerService.delete(id);
            ManagerDTO managerDTO = managerService.managerToDTO(managerList);
            return ResponseEntity.ok(managerDTO);
        } catch (Exception e) {
            log.error("환자관리자 정보 삭제에 실패했습니다. : " + e.getStackTrace());
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
