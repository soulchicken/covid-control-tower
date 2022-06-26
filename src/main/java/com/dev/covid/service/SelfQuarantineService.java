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
		SelfQuarantine madenSelfQuarantine = selfQuarantineRepository.save(newSelfQuarantine);
		patient.setSelfQuarantine(madenSelfQuarantine);
		patientRepository.save(patient);
		return madenSelfQuarantine;
	}

	public List<SelfQuarantine> delete(Long id) throws Exception {
		try {
			SelfQuarantine foundSelfQuarantine = selfQuarantineRepository.findById(id).get();
			selfQuarantineRepository.delete(foundSelfQuarantine);
			return selfQuarantineRepository.findAll();
		} catch (Exception e) {
			throw new Exception("환자 정보가 없다는");
		}

	}

	public SelfQuarantine put(SelfQuarantine selfQuarantine) throws Exception{
		try {
			SelfQuarantine foundSelfQuarantine = selfQuarantineRepository.findById(selfQuarantine.getSelfQuarantineId()).get();
			foundSelfQuarantine.setSelfQuarantineDate(selfQuarantine.getSelfQuarantineDate());
			foundSelfQuarantine.setSelfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease());
			return selfQuarantineRepository.save(foundSelfQuarantine);

		} catch (Exception e) {
			throw new Exception("환자 정보가 없다는");
		}
	}

	public List<SelfQuarantine> findByselfQuarantineDateBetween(String start, String end) {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		System.out.println("스타트 데이트 "+start);
		System.out.println("엔드 데이트 "+end);
		return selfQuarantineRepository.findByselfQuarantineDateBetween(startDate,endDate);

	}

	public List<SelfQuarantine> findByselfQuarantineReleaseBetween(String start, String end) {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);
		return selfQuarantineRepository.findByselfQuarantineReleaseBetween(startDate,endDate);

	}

	public SelfQuarantine findById(Long id) {
		try {			
			SelfQuarantine selfQuarantine = selfQuarantineRepository.findById(id).orElseThrow(Exception::new);
			return selfQuarantine;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
