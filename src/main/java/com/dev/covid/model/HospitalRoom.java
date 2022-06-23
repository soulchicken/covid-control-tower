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

    private Long hospitalroomRoomnumber;


    @Column(name = "hospitalroom_capacity")
    private int hospitalroomCapacity;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hospital.class)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


}