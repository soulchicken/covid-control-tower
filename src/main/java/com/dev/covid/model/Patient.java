package com.dev.covid.model;

import lombok.*;

import javax.persistence.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "people_id", nullable = false)
    private Long peopleId;
    @Column(name = "people_name", nullable = false)
    private String peopleName;
    @Column(name = "people_gender", nullable = false)
    private String peopleGender;
    @Column(name = "people_age", nullable = false)
    private int peopleAge;
    @Column(name = "people_home", nullable = false)
    private String peopleHome;
    @Column(name = "people_phone", nullable = false, unique = true)
    private String peoplePhone;
    @Column(name = "people_isdanger", nullable = false)
    //nullable=notnull제약, unique=중복값 불가
    private char peopleIsDanger;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "patient", cascade = CascadeType.ALL)
    private SelfQuarantine selfQuarantine;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Manager.class)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hospital.class)
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;
}
