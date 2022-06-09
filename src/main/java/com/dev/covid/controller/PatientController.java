package com.dev.covid.controller;

import com.dev.covid.model.Patient;
import com.dev.covid.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
public class PatientController {
    @Autowired
    private Service service;

    /**
     * 전체 환자를 조회를 한다.
     * @return 전체 환자 목록
     */
    @GetMapping
    public List<Patient> findAll() {
        return service.findAll();
    }

    // post 요청
    /**
     * 할 일을 등록한다.
     * @param todo 새롭게 등록할 할 일 정보
     * @return 등록된 할 일 정보
     */
    @PostMapping
    public Patient save(@RequestBody Patient patient) {
        // @RequestBody - 클라이언트에서 보낸 값을 Todo의 필드와 맵핑해서 객체 형태로 바인딩
        System.out.println(patient);
        return service.save(patient);
    }

    // putMapping
    /**
     * 할 일을 정보를 갱신한다.
     *
     * @param todo 바꿀 새로운 할일 정보
     * @return 갱신된 할 일 정보
     */
    @PutMapping
    public List<Patient> update(@RequestBody Patient patient) {
        return service.update(patient);
    }

    /**
     *
     * @param 삭제 할 환자 id
     * @return
     */
    @DeleteMapping("/{patientId}")
    public List<Patient> delete(@PathVariable("patientId") Long id) {
        System.out.println(id);
        return service.delete(id);
    }

}
