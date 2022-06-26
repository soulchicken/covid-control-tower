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

    public static SelfQuarantineDTO selfQuarantineToDTO(SelfQuarantine selfQuarantine){
        return SelfQuarantineDTO
                .builder()
                .patientName(selfQuarantine.getPatient().getPeopleName())
                .selfQuarantineId(selfQuarantine.getSelfQuarantineId())
                .selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
                .selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
                .build();
    }

}
