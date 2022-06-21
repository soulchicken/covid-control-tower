package com.dev.covid.model;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="self_quarantine")
public class SelfQuarantine {
	
// 지금은 아이디값 1씩 자동증가로 해놨지만 이후에 OneToOne으로 전환 
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "people_id")
	private Patient patient;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long selfQuarantineId;

	@Column(name = "self_quarantine_people_name")
	private String selfQuarantineName;

	@Column(name = "self_quarantine_date")
	private Date selfQuarantineDate;
	
	@Column(name = "self_quarantine_release")
	private Date selfQuarantineRelease;

	@Override
	public String toString() {
		return "SelfQuarantine [patientPeopleId=" + ", selfQuarantineDate=" + selfQuarantineDate
				+ ", selfQuarantineRelease=" + selfQuarantineRelease + ", selfQuarantineName=" + selfQuarantineName
				+  "]";
	}

	
	
}
