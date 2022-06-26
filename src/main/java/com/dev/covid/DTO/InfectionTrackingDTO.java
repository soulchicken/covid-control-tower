package com.dev.covid.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfectionTrackingDTO {
    private Long infectionTrackingId;
    private String infectionTrackingArea;
    private Date infectionTrackingDate;
    private String infectionTrackingCause;
    private Long patientPeopleId;

}
