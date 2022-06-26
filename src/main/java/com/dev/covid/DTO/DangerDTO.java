package com.dev.covid.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DangerDTO {
    private Long dangerId;
    private Long patientId;
    private LocalDate dangerCareDate;
    private LocalDate dangerCareRelease;
    private Long hospitalRoomnumber;

}
