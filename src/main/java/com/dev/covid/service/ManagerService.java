package com.dev.covid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.covid.model.Manager;
import com.dev.covid.repository.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository repository;
	
	public List<Manager> findAll(){
		return repository.findAll();
	}

	public Manager save(Manager manager) {
		return repository.save(manager);
	}

	public List<Manager> put(Manager manager) {
		final Optional<Manager> foundManager = repository.findById(manager.getManagerId());
		foundManager.ifPresent(updateManager -> {
			updateManager.setManagerId(manager.getManagerId());
			updateManager.setManagerName(manager.getManagerName());
			updateManager.setManagerPhone(manager.getManagerPhone());
			
			repository.save(updateManager);
		});
		return repository.findAll();
	}

	public List<Manager> delete(Long id) {
		final Optional <Manager> foundManager = repository.findById(id);
		foundManager.ifPresent(manager -> {
			repository.delete(manager);
		});
		return repository.findAll();
	}


}
