package com.dev.covid.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dev.covid.DTO.SelfQuarantineDTO;
import com.dev.covid.model.Patient;
import com.dev.covid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.repository.SelfQuarantineRepository;

@Service
public class SelfQuarantineService {
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private SelfQuarantineRepository selfQuarantineRepository;

	@Autowired
	private PatientRepository patientRepository;

	public List<SelfQuarantine> findAll() {
		return selfQuarantineRepository.findAll();
	}

	public SelfQuarantine save(SelfQuarantineDTO selfQuarantineDTO) {
		Patient patient = patientRepository.findByPeopleName(selfQuarantineDTO.getPatientName());
		SelfQuarantine newSelfQuarantine = SelfQuarantine
				.builder()
				.selfQuarantineDate(selfQuarantineDTO.getSelfQuarantineDate())
				.selfQuarantineRelease(selfQuarantineDTO.getSelfQuarantineRelease())
				.selfQuarantineName(patient.getPeopleName())
				.patient(patient)
				.build();
		SelfQuarantine madenSelfQuarantine = selfQuarantineRepository.save(newSelfQuarantine);
		patient.setSelfQuarantine(madenSelfQuarantine);
		patientRepository.save(patient);
		return madenSelfQuarantine;
	}

	public List<SelfQuarantine> delete(Long id) {
		final Optional<SelfQuarantine> foundSelfQuarantine = selfQuarantineRepository.findById(id);
		foundSelfQuarantine.ifPresent(selfQuarantine -> selfQuarantineRepository.delete(selfQuarantine));
		return selfQuarantineRepository.findAll();
	}

	public List<SelfQuarantine> put(SelfQuarantine selfQuarantine) {
		final Optional<SelfQuarantine> foundSelfQuarantine = selfQuarantineRepository.findById(selfQuarantine.getSelfQuarantineId());
		foundSelfQuarantine.ifPresent(updateSelfQuarantine -> {
			updateSelfQuarantine.setSelfQuarantineDate(selfQuarantine.getSelfQuarantineDate());
			updateSelfQuarantine.setSelfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease());
			selfQuarantineRepository.save(updateSelfQuarantine);
		});
		return selfQuarantineRepository.findAll();
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

}
