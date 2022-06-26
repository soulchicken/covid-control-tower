package com.dev.covid.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DangerDTO {
    private Long dangerId;
    private Long patientId;
    private Date dangerCareDate;
    private Date dangerCareRelease;
    private Long hospitalRoomnumberId;

}
