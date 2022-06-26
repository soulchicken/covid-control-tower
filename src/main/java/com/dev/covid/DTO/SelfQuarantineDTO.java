package com.dev.covid.DTO;

import com.dev.covid.model.SelfQuarantine;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelfQuarantineDTO {
    private String patientName;
    private Long selfQuarantineId;
    private LocalDate selfQuarantineDate;
    private LocalDate selfQuarantineRelease;

}
