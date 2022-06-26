package com.dev.covid.model;

import lombok.*;


import javax.persistence.*;
import java.util.List;


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
    private long hospitalroomCapacity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hospital.class)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hospitalRoom")
    private List<Danger> dangerList;

}