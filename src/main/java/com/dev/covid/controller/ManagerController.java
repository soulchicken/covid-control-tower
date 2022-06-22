package com.dev.covid.controller;

import java.util.ArrayList;
import java.util.List;

import com.dev.covid.DTO.ManagerDTO;
import com.dev.covid.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.covid.model.Manager;
import com.dev.covid.service.ManagerService;

@RestController
@RequestMapping("manager")
public class ManagerController {
	
	@Autowired
	private ManagerService service;
	
	@GetMapping
	public List<ManagerDTO> findAll(){
		List<Manager> managerList = service.findAll();
		List<ManagerDTO> managerDTOList = new ArrayList<>();
		for (Manager manager : managerList){
			List<String> patientNameList = new ArrayList<>();
			for (Patient patient : manager.getPatientList()){
				patientNameList.add(patient.getPeopleName());
			}
			managerDTOList.add(
					ManagerDTO
							.builder()
							.managerId(manager.getManagerId())
							.managerName(manager.getManagerName())
							.managerPhone(manager.getManagerPhone())
							.patientNameList(patientNameList)
							.build()
			);
		}
		 return managerDTOList;
	}
	
	@PostMapping
	public Manager save(@RequestBody Manager manager) {
		return service.save(manager);
	}
	
	@PutMapping
	public List<ManagerDTO> put(@RequestBody Manager updateManager) {

		List<Manager> managerList = service.put(updateManager);
		List<ManagerDTO> managerDTOList = new ArrayList<>();
		for (Manager manager : managerList){
			List<String> patientNameList = new ArrayList<>();
			for (Patient patient : manager.getPatientList()){
				patientNameList.add(patient.getPeopleName());
			}
			managerDTOList.add(
					ManagerDTO
							.builder()
							.managerId(manager.getManagerId())
							.managerName(manager.getManagerName())
							.managerPhone(manager.getManagerPhone())
							.patientNameList(patientNameList)
							.build()
			);
		}
		return managerDTOList;

	}
	
	@DeleteMapping("/{id}")
	public List<ManagerDTO> delete(@PathVariable("id") Long id) {
		List<ManagerDTO> managerDTOList = new ArrayList<>();
		List<Manager> managerList = service.delete(id);
		for (Manager manager : managerList){
			List<String> patientNameList = new ArrayList<>();
			for (Patient patient : manager.getPatientList()){
				patientNameList.add(patient.getPeopleName());
			}
			managerDTOList.add(
					ManagerDTO
							.builder()
							.managerId(manager.getManagerId())
							.managerName(manager.getManagerName())
							.managerPhone(manager.getManagerPhone())
							.patientNameList(patientNameList)
							.build()
			);
		}
		return managerDTOList;
	}
	
	
}
