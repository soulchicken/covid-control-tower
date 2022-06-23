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
@Table(name = "hospitalroom")
public class HospitalRoom {
    @Id
    @Column(name = "hospitalroom_roomnumber")
    private int hospitalroomRoomnumber;

    @Column(name = "hospitalroom_capacity")
    private int hospitalroomCapacity;

    @Column(name = "hospital_hospital_id", nullable = false)
    private Long hospitalhospitalId;

}