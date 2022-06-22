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
public class ManagerDTO {
    private Long managerId;
    private String managerName;
    private String managerPhone;
    private List<String> patientNameList;
}
