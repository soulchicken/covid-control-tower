package com.dev.covid.DTO;

import com.dev.covid.model.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalRoomDTO {
    private long hospitalroomRoomnumber;
    private long hospitalroomCapacity;
    private List<HospitalDTO> hospitalDTOList;

}
