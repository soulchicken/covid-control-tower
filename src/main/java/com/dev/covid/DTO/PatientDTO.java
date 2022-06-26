package com.dev.covid.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long peopleId;
    private String peopleName;
    private String peopleGender;
    private int peopleAge;
    private String peopleHome;
    private String peoplePhone;
    private SelfQuarantineDTO selfQuarantineDTO;
    private Long hospitalId;
    private Long managerId;
    private List<InfectionTrackingDTO> infectionTrackingDTOList;
}
