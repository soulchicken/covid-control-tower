package com.dev.covid.controller;


import com.dev.covid.model.HospitalRoom;
import com.dev.covid.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hospitalroom")
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService hospitalroomService;

    // 조회
    @GetMapping
    public List<HospitalRoom> findAll() {
        return hospitalroomService.findAll();
    }

    // 삽입
    @PostMapping
    public HospitalRoom save(@RequestBody HospitalRoom hospitalroom) {
        return hospitalroomService.save(hospitalroom);
    }

    // 수정
    @PutMapping
    public List<HospitalRoom> update(@RequestBody HospitalRoom hospitalroom) {
        return hospitalroomService.update(hospitalroom);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public List<HospitalRoom> delete(@PathVariable("id") Long id) {
        return hospitalroomService.delete(id);
    }
}
