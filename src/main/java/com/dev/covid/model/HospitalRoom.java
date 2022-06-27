package com.dev.covid.model;

import lombok.*;


import javax.persistence.*;
import java.util.List;


@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "hospitalroom")
public class HospitalRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospitalroom_number")
    private Long hospitalroomRoomnumber;

    @Column(name = "hospitalroom_capacity")
    private long hospitalroomCapacity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hospital.class)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hospitalRoom")
    private List<Danger> dangerList;

}