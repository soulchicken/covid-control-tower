package com.dev.covid.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "hospital_room")
    private int hospitalRoom;

    @Column(name = "hospital_patientnum")
    private int hospitalPatientnum;

    @Column(name = "hospital_roomlimit")
    private int hospitalRoomlimit;
}
