package com.dev.covid.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalDTO {
    private Long hospitalId;
    private String hospitalName;
    private int hospitalPatientnum;
    private int hospitalRoomlimit;
    private List<Long> hospitalRoomNumberList;

}