package com.dev.covid.DTO;

import lombok.AllArgsConstructor;

import java.util.Date;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelfQuarantineDTO {
    private String patientName;
    private Long selfQuarantineId;
    private Date selfQuarantineDate;
    private Date selfQuarantineRelease;
}
