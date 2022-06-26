package com.dev.covid.controller;

import com.dev.covid.DTO.DangerDTO;
import com.dev.covid.DTO.ResponseDTO;
import com.dev.covid.model.Danger;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.model.InfectionTracking;
import com.dev.covid.service.DangerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("danger")
public class DangerController {

    @Autowired
    private DangerService dangerService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try{
            List<Danger> dangerList = dangerService.findAll();
            List<DangerDTO> dangerDTOList = new ArrayList<>();
            for (Danger danger : dangerList) {
                dangerDTOList.add(
                        dangerService.dangerDTO(danger)
                );
            }
            return ResponseEntity.ok(dangerDTOList);
        }catch (Exception e){
            log.error("고위험군 환자 정보 조회에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DangerDTO dangerDTO) {
        try{
            Danger danger = dangerService.save(dangerDTO);
            DangerDTO newDangerDTO = dangerService.dangerDTO(danger);
            return ResponseEntity.ok(newDangerDTO);
        }catch (Exception e){
            log.error("고위험군 환자 정보 등록에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DangerDTO updateDanger) {
        try{
            Danger danger = dangerService.update(updateDanger);
            DangerDTO dangerDTO = dangerService.dangerDTO(danger);

            return ResponseEntity.ok(dangerDTO);
        }catch (Exception e){
            log.error("고위험군 환자 정보 변경에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try{
            Danger danger = dangerService.delete(id);
            DangerDTO dangerDTO = dangerService.dangerDTO(danger);

            return ResponseEntity.ok(dangerDTO);
        }catch (Exception e){
            log.error("고위험군 환자 정보 변경에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
