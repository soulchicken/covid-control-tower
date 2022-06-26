package com.dev.covid.service;

import java.lang.management.MemoryNotificationInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dev.covid.DTO.ManagerDTO;
import com.dev.covid.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.covid.model.Manager;
import com.dev.covid.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	public List<Manager> findAll() {
		return managerRepository.findAll();
	}

	public Manager save(ManagerDTO managerDTO){
		return managerRepository.save(CreateManager(managerDTO));
	}

	public Manager update(ManagerDTO managerDTO) throws Exception{
			Manager foundManager = managerRepository.findById(managerDTO.getManagerId())
					.orElseThrow(Exception::new);
			foundManager.setManagerName(managerDTO.getManagerName());
			foundManager.setManagerPhone(managerDTO.getManagerPhone());
			return managerRepository.save(foundManager);
	}

	public Manager delete(Long id) throws Exception{
			final Manager manager = managerRepository.findById(id)
					.orElseThrow(Exception::new);
			managerRepository.delete(manager);
			return manager;
	}

	public ManagerDTO managerToDTO(Manager manager) {
		return ManagerDTO
				.builder()
				.managerId(manager.getManagerId())
				.managerPhone(manager.getManagerPhone())
				.managerName(manager.getManagerName())
				.build();
	}

	public ManagerDTO managerToDTO(Manager manager, List<String> patientNameList) {
		return ManagerDTO
				.builder()
				.managerId(manager.getManagerId())
				.managerPhone(manager.getManagerPhone())
				.managerName(manager.getManagerName())
				.patientNameList(patientNameList)
				.build();
	}

	public List<ManagerDTO> managerToDTOList(List<Manager> managerList) {
		List<ManagerDTO> managerDTOList = new ArrayList<>();
		for (Manager manager : managerList) {
			List<String> patientNameList = new ArrayList<>();
			for (Patient patient : manager.getPatientList()) {
				patientNameList.add(patient.getPeopleName());
			}
			managerDTOList.add(managerToDTO(manager,patientNameList));
		}
		return managerDTOList;
	}

	public Manager CreateManager(ManagerDTO managerDTO){
		return Manager.builder()
				.managerName(managerDTO.getManagerName())
				.managerPhone(managerDTO.getManagerPhone())
				.build();
	}
}


