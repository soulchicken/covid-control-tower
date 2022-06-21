package com.dev.covid.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="self_quarantine")
public class SelfQuarantine {
	
// 지금은 아이디값 1씩 자동증가로 해놨지만 이후에 OneToOne으로 전환 
//	@Id
//	@OneToOne
//	@JoinColumn(mappedBy = "selfQuarantine")
//	private Patient patient;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_people_id")
	private long patientPeopleId;

	@Column(name = "self_quarantine_people_name")
	private String selfQuarantineName;
	
	@Column(name = "self_quarantine_phone_number")	
	private String selfQuarantinephoneNumber;

	@Column(name = "self_quarantine_date")
	private Date selfQuarantineDate;
	
	@Column(name = "self_quarantine_release")
	private Date selfQuarantineRelease;

	@Override
	public String toString() {
		return "SelfQuarantine [patientPeopleId=" + patientPeopleId + ", selfQuarantineDate=" + selfQuarantineDate
				+ ", selfQuarantineRelease=" + selfQuarantineRelease + ", selfQuarantineName=" + selfQuarantineName
				+ ", selfQuarantinephoneNumber=" + selfQuarantinephoneNumber + "]";
	}

	
	
}
