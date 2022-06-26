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
@Table(name = "infectiontracking")
public class InfectionTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "infectiontracking_id", nullable = false)
    private Long infectionTrackingId;

    @Column(name = "infectiontracking_name")
    private String infectionTrackingName;

    @Column(name = " infectiontracking_area")
    private String infectionTrackingArea;

    @Column(name = "infectiontracking_date")
    private Date infectionTrackingDate;

    @Column(name = "infectiontracking_cause")
    private String infectionTrackingCause;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Patient.class) // student와 major의 관계는 다대일 (N:1)
    @JoinColumn(name = "people_id")
    private Patient patient;


}
