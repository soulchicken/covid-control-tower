package com.dev.covid.model;

import lombok.*;


import javax.persistence.*;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@ToString
@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id", nullable = false)



    private Long hospitalId;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "hospital_room")
    private int hospitalRoom;

    @Column(name = "hospital_patientnum")
    private int hospitalPatientnum;


    @Column(name = "hospital_roomlimit")
    private int hospitalRoomlimit;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<HospitalRoom> hospitalRoomList;

}
