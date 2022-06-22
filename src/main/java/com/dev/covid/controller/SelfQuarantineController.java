package com.dev.covid.controller;

import java.util.ArrayList;
import java.util.List;

import com.dev.covid.DTO.SelfQuarantineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.SelfQuarantineService;

@RestController
@RequestMapping("selfquarantine")
public class SelfQuarantineController {
	
	@Autowired
	private SelfQuarantineService service;
	
	@GetMapping
	public List<SelfQuarantineDTO> findAll() {
		List<SelfQuarantine> selfQuarantineList = service.findAll();
		List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
		for (SelfQuarantine selfQuarantine : selfQuarantineList){
			selfQuarantineDTOList.add(SelfQuarantineDTO
							.builder()
							.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
							.selfQuarantineId(selfQuarantine.getSelfQuarantineId())
							.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
							.patientName(selfQuarantine.getPatient().getPeopleName())
							.build()
			);
		}
		return selfQuarantineDTOList;
	}
	
	@GetMapping("/{id}")
	public SelfQuarantineDTO findById(@PathVariable("id") Long id) {

		SelfQuarantine selfQuarantine = service.findById(id);

		SelfQuarantineDTO selfQuarantineDTO = SelfQuarantineDTO
								.builder()
								.patientName(selfQuarantine.getPatient().getPeopleName())
								.selfQuarantineId(selfQuarantine.getSelfQuarantineId())
								.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
								.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
								.build();
		return selfQuarantineDTO;
	}
	
	@GetMapping("/name/{name}")
	public List<SelfQuarantineDTO> findByselfQuarantineName(@PathVariable("name") String name) {
		List<SelfQuarantine> selfQuarantineList = service.findByselfQuarantineName(name);

		List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
		for (SelfQuarantine selfQuarantine : selfQuarantineList){
			selfQuarantineDTOList.add(
					SelfQuarantineDTO
							.builder()
							.patientName(selfQuarantine.getPatient().getPeopleName())
							.selfQuarantineId(selfQuarantine.getSelfQuarantineId())
							.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
							.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
							.build()
			);
		}
		return selfQuarantineDTOList;
	}
	
	@GetMapping("date")
	public List<SelfQuarantineDTO> findByselfQuarantineDateBetween(@RequestParam("start") String start, @RequestParam("end") String end) {

		List<SelfQuarantine> selfQuarantineList = service.findByselfQuarantineDateBetween(start,end);

		List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
		for (SelfQuarantine selfQuarantine : selfQuarantineList){
			selfQuarantineDTOList.add(
					SelfQuarantineDTO
							.builder()
							.patientName(selfQuarantine.getPatient().getPeopleName())
							.selfQuarantineId(selfQuarantine.getSelfQuarantineId())
							.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
							.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
							.build()
			);
		}
		return selfQuarantineDTOList;
	}
	
	@GetMapping("release")
	public List<SelfQuarantineDTO> findByselfQuarantineReleaseBetween(@RequestParam("start") String start, @RequestParam("end") String end) {
		List<SelfQuarantine> selfQuarantineList = service.findByselfQuarantineReleaseBetween(start,end);

		List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
		for (SelfQuarantine selfQuarantine : selfQuarantineList){
			selfQuarantineDTOList.add(
					SelfQuarantineDTO
							.builder()
							.patientName(selfQuarantine.getPatient().getPeopleName())
							.selfQuarantineId(selfQuarantine.getSelfQuarantineId())
							.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
							.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
							.build()
			);
		}
		return selfQuarantineDTOList;
	}
	
	@PostMapping
	public SelfQuarantineDTO save(@RequestBody SelfQuarantineDTO selfQuarantineDTO) {
		SelfQuarantine newSelfQuarantine = service.save(selfQuarantineDTO);
		SelfQuarantineDTO newSelfQuarantineDTO = SelfQuarantineDTO
				.builder()
				.patientName(newSelfQuarantine.getPatient().getPeopleName())
				.selfQuarantineDate(newSelfQuarantine.getSelfQuarantineDate())
				.selfQuarantineRelease(newSelfQuarantine.getSelfQuarantineRelease())
				.selfQuarantineId(newSelfQuarantine.getSelfQuarantineId())
				.build();
		return newSelfQuarantineDTO;
	}
	
	@PutMapping
	public List<SelfQuarantineDTO> put(@RequestBody SelfQuarantine putSelfQuarantine) {
		List<SelfQuarantine> selfQuarantineList = service.put(putSelfQuarantine);

		List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
		for (SelfQuarantine selfQuarantine : selfQuarantineList){
			selfQuarantineDTOList.add(
					SelfQuarantineDTO
							.builder()
							.patientName(selfQuarantine.getPatient().getPeopleName())
							.selfQuarantineId(selfQuarantine.getSelfQuarantineId())
							.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
							.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
							.build()
			);
		}
		return selfQuarantineDTOList;
	}
	
	@DeleteMapping("/{id}")
	public List<SelfQuarantineDTO> delete(@PathVariable("id") Long id) {
		List<SelfQuarantine> selfQuarantineList = service.delete(id);
		List<SelfQuarantineDTO> selfQuarantineDTOList = new ArrayList<>();
		for (SelfQuarantine selfQuarantine : selfQuarantineList){
			selfQuarantineDTOList.add(
					SelfQuarantineDTO
							.builder()
							.patientName(selfQuarantine.getPatient().getPeopleName())
							.selfQuarantineId(selfQuarantine.getSelfQuarantineId())
							.selfQuarantineDate(selfQuarantine.getSelfQuarantineDate())
							.selfQuarantineRelease(selfQuarantine.getSelfQuarantineRelease())
							.build()
			);
		}
		return selfQuarantineDTOList;
	}
	
}
