package com.dev.covid.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Date dangerCareDate;
    @Column(name = "danger_care_release")
    private Date dangerCareRelease;
    @Column(name = "hospitalroom_roomnumber")

    private Long hospitalRoomnumber;



    
}
