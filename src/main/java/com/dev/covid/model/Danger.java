package com.dev.covid.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "danger")
public class Danger {
    @Id
    @Column(name = "patient_people_id")
    private Long patientPeopleId;
    @Column(name = "danger_care_date")
    private Date dangerCareDate;
    @Column(name = "danger_care_release")
    private Date dangerCareRelease;
    @Column(name = "hospitalroom_roomnumber")
    private Long hospitalroomRoomnumber;
}
