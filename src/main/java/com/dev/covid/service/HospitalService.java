package com.dev.covid.service;

import com.dev.covid.DTO.HospitalDTO;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.repository.HospitalRepository;
import com.dev.covid.repository.HospitalRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HospitalRoomRepository hospitalRoomRepository;

    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    public Hospital save(HospitalDTO hospitalDTO) throws Exception {

        Hospital hospital = makeHospital(hospitalDTO);
        return hospitalRepository.save(hospital);
    }

    public Hospital update(HospitalDTO hospitalDTO) throws Exception {
        Hospital findHospital = hospitalRepository.findById(hospitalDTO.getHospitalId()).orElseThrow(Exception::new);
        findHospital.setHospitalName(hospitalDTO.getHospitalName());
        findHospital.setHospitalPatientnum((hospitalDTO.getHospitalPatientnum()));
        findHospital.setHospitalRoomlimit(hospitalDTO.getHospitalRoomlimit());
        return hospitalRepository.save(findHospital);
    }

    public Hospital delete(Long id) throws  Exception {
        final Hospital hospital = hospitalRepository.findById(id).orElseThrow(Exception::new);
        hospitalRepository.delete(hospital);
        return hospital;
    }

    public HospitalDTO hospitalDTO(Hospital hospital){
        return HospitalDTO
                .builder()
                .hospitalId(hospital.getHospitalId())
                .hospitalName(hospital.getHospitalName())
                .hospitalPatientnum(hospital.getHospitalPatientnum())
                .hospitalRoomlimit(hospital.getHospitalRoomlimit())
                .build();
    }
    public Hospital makeHospital(HospitalDTO hospitalDTO){
        return Hospital
                .builder()
                .hospitalId(hospitalDTO.getHospitalId())
                .hospitalName(hospitalDTO.getHospitalName())
                .hospitalPatientnum(hospitalDTO.getHospitalPatientnum())
                .hospitalRoomlimit(hospitalDTO.getHospitalRoomlimit())
                .build();
    }
}