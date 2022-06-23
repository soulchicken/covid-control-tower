package com.dev.covid.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manager_id", nullable = false)
	private Long managerId;
	
	@Column(name = "manager_name")
	private String managerName;
	
	@Column(name = "manager_phone")
	private String managerPhone;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "manager")
	private List<Patient> patientList;
}
