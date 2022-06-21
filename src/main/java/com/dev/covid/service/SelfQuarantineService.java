package com.dev.covid.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public SelfQuarantineDTO save(SelfQuarantine selfQuarantine) {
		String name = selfQuarantine.getSelfQuarantineName();
		Patient patient = patientRepository.findByPeopleName(name);
		SelfQuarantine newSelfQuarantine = SelfQuarantine
				.builder()
				.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
				.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
				.selfQuarantineName(patient.getPeopleName())
				.patient(patient)
				.build();
		patient.setSelfQuarantine(newSelfQuarantine);
		selfQuarantineRepository.save(newSelfQuarantine);
		SelfQuarantineDTO selfQuarantineDTO = SelfQuarantineDTO
				.builder()
				.patientName(patient.getPeopleName())
				.selfQuarantineId(newSelfQuarantine.getSelfQuarantineId())
				.selfQuarantineDate(newSelfQuarantine.getSelfQuarantineDate())
				.selfQuarantineRelease(newSelfQuarantine.getSelfQuarantineRelease())
				.build();
		return selfQuarantineDTO;
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
		try {
			Date startDate = formatter.parse(start);
			Date endDate = formatter.parse(end);
			return selfQuarantineRepository.findByselfQuarantineDateBetween(startDate,endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<SelfQuarantine> findByselfQuarantineReleaseBetween(String start, String end) {
		try {
			Date startDate = formatter.parse(start);
			Date endDate = formatter.parse(end);
			return selfQuarantineRepository.findByselfQuarantineReleaseBetween(startDate,endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
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
