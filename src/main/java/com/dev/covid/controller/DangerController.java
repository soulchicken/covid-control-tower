package com.dev.covid.controller;

import com.dev.covid.DTO.DangerDTO;
import com.dev.covid.DTO.ResponseDTO;
import com.dev.covid.model.Danger;
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

    @GetMapping("/date")
    public List<DangerDTO> findBydangerCareDateBetween(@RequestParam("start") String start, @RequestParam("end") String end){
        List<Danger> dangerList = dangerService.findBydangerCareDateBetween(start, end);

        List<DangerDTO> dangerDTOList = new ArrayList<>();
        for(Danger danger : dangerList){
            dangerDTOList.add(
                    dangerService.dangerDTO(danger)
            );
        }
        return dangerDTOList;
    }

    @GetMapping("/release")
    public List<DangerDTO> findBydangerCareReleaseBetween(@RequestParam("start") String start, @RequestParam("end") String end){
        List<Danger> dangerList = dangerService.findBydangerCareReleaseBetween(start, end);

        List<DangerDTO> dangerDTOList = new ArrayList<>();
        for(Danger danger : dangerList){
            dangerDTOList.add(
                   dangerService.dangerDTO(danger)
            );
        }
        return dangerDTOList;
    }

    @GetMapping("/{id}")
    public DangerDTO findById(@PathVariable("id") Long id){
        Danger danger = dangerService.findById(id);

        DangerDTO dangerDTO = dangerService.dangerDTO(danger);
        return dangerDTO;
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
    public ResponseEntity<?> update(@RequestBody DangerDTO updateDangerDTO) {
        try{
            Danger dangerList = dangerService.update(updateDangerDTO);
            List<DangerDTO> dangerDTOList = new ArrayList<>();

                dangerDTOList.add(
                        dangerService.dangerDTO(dangerList)
                );
            return ResponseEntity.ok(dangerDTOList);
        }catch (Exception e){
            log.error("고위험군 환자 정보 변경에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
           Danger dangerList = dangerService.delete(id);
            List<DangerDTO> dangerDTOList = new ArrayList<>();
                dangerDTOList.add(
                        dangerService.dangerDTO(dangerList)
                );
            return ResponseEntity.ok(dangerDTOList);
        }catch (Exception e){
            log.error("고위험군 환자 정보 삭제에 실패했습니다. :" + e.getMessage());
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return  ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
