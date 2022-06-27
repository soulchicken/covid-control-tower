package com.dev.covid.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "danger")
public class Danger {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "people_id")
    private Patient patient;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "danger_id")
    private Long dangerId;

    @Column(name = "danger_care_date")
    private LocalDate dangerCareDate;

    @Column(name = "danger_care_release")
    private LocalDate dangerCareRelease;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = HospitalRoom.class)
    @JoinColumn(name = "hospitalroom_roomnumber")
    private HospitalRoom hospitalRoom;
    
}
