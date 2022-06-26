package com.dev.covid.controller;

import java.util.List;

import com.dev.covid.DTO.ResponseDTO;
import com.dev.covid.DTO.SelfQuarantineDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.SelfQuarantineService;

@Slf4j
@RestController
@RequestMapping("selfquarantine")
public class SelfQuarantineController {

    @Autowired
    private SelfQuarantineService selfQuarantineService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<SelfQuarantine> selfQuarantineList = selfQuarantineService.findAll();
        if (selfQuarantineList.size() == 0){
            log.error("자가격리자가 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당되는 이름이 없습니다.").build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
        List<SelfQuarantineDTO> selfQuarantineDTOList = selfQuarantineService.selfQuarantineListToDTOList(selfQuarantineList);
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            SelfQuarantine selfQuarantine = selfQuarantineService.findById(id);
            SelfQuarantineDTO selfQuarantineDTO = selfQuarantineService.selfQuarantineToDTO(selfQuarantine);
            return ResponseEntity.ok(selfQuarantineDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당되는 id가 없습니다.").build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByselfQuarantineName(@PathVariable("name") String name) {
        List<SelfQuarantine> selfQuarantineList = selfQuarantineService.findByselfQuarantineName(name);

        if (selfQuarantineList.size() == 0){
            log.error("해당되는 이름이 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당되는 이름이 없습니다.").build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }

        List<SelfQuarantineDTO> selfQuarantineDTOList = selfQuarantineService.selfQuarantineListToDTOList(selfQuarantineList);
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @GetMapping("date")
    public ResponseEntity<?> findByselfQuarantineDateBetween(@RequestParam("start") String start, @RequestParam("end") String end) {

        List<SelfQuarantine> selfQuarantineList = selfQuarantineService.findByselfQuarantineDateBetween(start, end);

        if (selfQuarantineList.size() == 0){
            log.error("해당 날짜에 대한 기록이 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당 날짜에 대한 기록이 없습니다.").build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }

        List<SelfQuarantineDTO> selfQuarantineDTOList = selfQuarantineService.selfQuarantineListToDTOList(selfQuarantineList);
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @GetMapping("release")
    public ResponseEntity<?> findByselfQuarantineReleaseBetween(@RequestParam("start") String start, @RequestParam("end") String end) {
        List<SelfQuarantine> selfQuarantineList = selfQuarantineService.findByselfQuarantineReleaseBetween(start, end);

        if (selfQuarantineList.size() == 0){
            log.error("해당 날짜에 대한 기록이 없습니다.");
            ResponseDTO responseDTO = ResponseDTO.builder().error("해당 날짜에 대한 기록이 없습니다.").build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
        List<SelfQuarantineDTO> selfQuarantineDTOList = selfQuarantineService.selfQuarantineListToDTOList(selfQuarantineList);
        return ResponseEntity.ok(selfQuarantineDTOList);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SelfQuarantineDTO selfQuarantineDTO) {
        try{
            SelfQuarantine newSelfQuarantine = selfQuarantineService.save(selfQuarantineDTO);
            SelfQuarantineDTO newSelfQuarantineDTO = selfQuarantineService.selfQuarantineToDTO(newSelfQuarantine);
            return ResponseEntity.ok(newSelfQuarantineDTO);
        } catch (Exception e) {
            log.error("자가격리자 정보 입력에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody SelfQuarantine putSelfQuarantine) {
        try {
            SelfQuarantine selfQuarantine = selfQuarantineService.put(putSelfQuarantine);
            SelfQuarantineDTO selfQuarantineDTO = selfQuarantineService.selfQuarantineToDTO(selfQuarantine);
            return ResponseEntity.ok(selfQuarantineDTO);
        } catch (Exception e) {
            log.error("자가격리자 정보 입력에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            List<SelfQuarantine> selfQuarantineList = selfQuarantineService.delete(id);
            List<SelfQuarantineDTO> selfQuarantineDTOList = selfQuarantineService.selfQuarantineListToDTOList(selfQuarantineList);
            return ResponseEntity.ok(selfQuarantineDTOList);
        } catch (Exception e) {
            log.error("자가격리자 정보 입력에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }
    
}
