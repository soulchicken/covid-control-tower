package com.dev.covid.controller;


import com.dev.covid.model.Hospital;
import com.dev.covid.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // 조회
    @GetMapping
    public List<Hospital> findAll() {
        return hospitalService.findAll();
    }

    // 삽입
    @PostMapping
    public Hospital save(@RequestBody Hospital hospital) {
        return hospitalService.save(hospital);
    }

    // 수정
    @PutMapping
    public List<Hospital> update(@RequestBody Hospital hospital) {
        return hospitalService.update(hospital);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public List<Hospital> delete(@PathVariable("id") Long id) {
        return hospitalService.delete(id);
    }
}
