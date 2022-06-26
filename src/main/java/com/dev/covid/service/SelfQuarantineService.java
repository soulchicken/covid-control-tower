package com.dev.covid.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.*;

import com.dev.covid.DTO.SelfQuarantineDTO;
import com.dev.covid.model.Patient;
import com.dev.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.repository.SelfQuarantineRepository;

@Service
public class SelfQuarantineService {

	@Autowired
	private SelfQuarantineRepository selfQuarantineRepository;

	@Autowired
	private PatientRepository patientRepository;

	public List<SelfQuarantine> findAll() {
		return selfQuarantineRepository.findAll();
	}

	public SelfQuarantine save(SelfQuarantineDTO selfQuarantineDTO) throws Exception{
		Patient patient = patientRepository.findByPeopleName(selfQuarantineDTO.getPatientName());
		if (patient == null){
			throw new Exception("일치하는 환자 이름이 없습니다.");
		}
		SelfQuarantine newSelfQuarantine = makeSelfQuarantine(patient, selfQuarantineDTO);
		SelfQuarantine madeSelfQuarantine = selfQuarantineRepository.save(newSelfQuarantine);
		patient.setSelfQuarantine(madeSelfQuarantine);
		patientRepository.save(patient);
		return madeSelfQuarantine;
	}

	public List<SelfQuarantine> delete(Long id) throws Exception {

		SelfQuarantine foundSelfQuarantine = selfQuarantineRepository.findById(id).orElseThrow(Exception::new);
		selfQuarantineRepository.delete(foundSelfQuarantine);
		return selfQuarantineRepository.findAll();
	}

	public SelfQuarantine put(SelfQuarantine selfQuarantine) throws Exception {
		SelfQuarantine foundSelfQuarantine = selfQuarantineRepository.findById(selfQuarantine.getSelfQuarantineId()).orElseThrow(Exception::new);
		foundSelfQuarantine.setSelfQuarantineDate(selfQuarantine.getSelfQuarantineDate());
		foundSelfQuarantine.setSelfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease());
		return selfQuarantineRepository.save(foundSelfQuarantine);

	}

	public List<SelfQuarantine> findByselfQuarantineDateBetween(String start, String end) {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		return selfQuarantineRepository.findByselfQuarantineDateBetween(startDate,endDate);

	}

	public List<SelfQuarantine> findByselfQuarantineReleaseBetween(String start, String end) {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		return selfQuarantineRepository.findByselfQuarantineReleaseBetween(startDate,endDate);

	}

	public SelfQuarantine findById(Long id) throws Exception {

			SelfQuarantine selfQuarantine = selfQuarantineRepository.findById(id).orElseThrow(Exception::new);
			return selfQuarantine;
	}

	public List<SelfQuarantine> findByselfQuarantineName(String name) {
		return selfQuarantineRepository.findByselfQuarantineName(name);
	}

	public SelfQuarantineDTO selfQuarantineToDTO(SelfQuarantine selfQuarantine){
		return SelfQuarantineDTO
				.builder()
				.patientName(selfQuarantine.getPatient().getPeopleName())
				.selfQuarantineId(selfQuarantine.getSelfQuarantineId())
				.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
				.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
				.build();
	}

	public List<SelfQuarantineDTO> selfQuarantineListToDTOList(List<SelfQuarantine> selfQuarantineList){
		List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
		for (SelfQuarantine selfQuarantine : selfQuarantineList) {
			selfQuarantineDTOList.add(selfQuarantineToDTO(selfQuarantine));
		}
		return selfQuarantineDTOList;
	}

	public SelfQuarantine makeSelfQuarantine(Patient patient, SelfQuarantineDTO selfQuarantineDTO){
		return SelfQuarantine
				.builder()
				.selfQuarantineDate(selfQuarantineDTO.getSelfQuarantineDate())
				.selfQuarantineRelease(selfQuarantineDTO.getSelfQuarantineRelease())
				.selfQuarantineName(patient.getPeopleName())
				.patient(patient)
				.build();
	}
}
