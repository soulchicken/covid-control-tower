package com.dev.covid.service;

import com.dev.covid.DTO.HospitalRoomDTO;
import com.dev.covid.model.Danger;
import com.dev.covid.model.Hospital;
import com.dev.covid.model.HospitalRoom;
import com.dev.covid.repository.HospitalRepository;
import com.dev.covid.repository.HospitalRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.management.MemoryNotificationInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalRoomService {
    @Autowired
    private HospitalRoomRepository repository;

    @Autowired
    private HospitalRepository hospitalRepository;

    public List<HospitalRoom> findAll() {

        return repository.findAll();
    }

    public HospitalRoom save(HospitalRoomDTO hospitalroomDTO)  throws Exception{
        return repository.save(CreateHospitalRoom(hospitalroomDTO));
    }

    public HospitalRoom update(HospitalRoomDTO hospitalRoomDTO) throws Exception {
            HospitalRoom foundHospitalRoom = repository.findById(hospitalRoomDTO.getHospitalroomRoomnumber())
                    .orElseThrow(Exception::new);
            foundHospitalRoom.setHospitalroomCapacity(hospitalRoomDTO.getHospitalroomCapacity());
            return repository.save(foundHospitalRoom);
    }

    public HospitalRoom delete(Long id) throws Exception{
            final HospitalRoom hospitalRoom = repository.findById(id)
                    .orElseThrow(Exception::new);
            repository.delete(hospitalRoom);
            return hospitalRoom;
    }

    public HospitalRoomDTO hospitalRoomToDTO(HospitalRoom hospitalRoom) {
            return HospitalRoomDTO
                    .builder()
                    .hospitalId(hospitalRoom.getHospital().getHospitalId())
                    .hospitalroomRoomnumber(hospitalRoom.getHospitalroomRoomnumber())
                    .hospitalroomCapacity(hospitalRoom.getHospitalroomCapacity())
                    .build();
    }

    public List<HospitalRoomDTO> hospitalRoomToDTOList(List<HospitalRoom> hospitalRoomList) {
        List<HospitalRoomDTO> hospitalRoomDTOList = new ArrayList<>();
        for (HospitalRoom hospitalRoom : hospitalRoomList) {
            List<String> dangerCareDateList = new ArrayList<>();
            for (Danger danger : hospitalRoom.getDangerList()) {
                dangerCareDateList.add(danger.getDangerCareDate().toString());
            }
            hospitalRoomDTOList.add(hospitalRoomToDTO(hospitalRoom));
        }
        return hospitalRoomDTOList;
    }

    public HospitalRoom CreateHospitalRoom(HospitalRoomDTO hospitalRoomDTO) throws Exception {
        Hospital hospital = hospitalRepository.findById(hospitalRoomDTO.getHospitalId()).orElseThrow(Exception ::new);
        return HospitalRoom.builder()
                .hospital(hospital)
                .hospitalroomCapacity(hospitalRoomDTO.getHospitalroomCapacity())
                .build();
    }
}
