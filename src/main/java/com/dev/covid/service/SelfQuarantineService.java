package com.dev.covid.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.repository.SelfQuarantineRepository;

@Service
public class SelfQuarantineService {
	
	@Autowired
	private SelfQuarantineRepository repository;
	
	public List<SelfQuarantine> findAll() {
		return repository.findAll();
	}

	public SelfQuarantine save(SelfQuarantine selfQuarantine) {
		return repository.save(selfQuarantine);
	}

	public List<SelfQuarantine> delete(Long id) {
		final Optional<SelfQuarantine> foundSelfQuarantine = repository.findById(id);
		foundSelfQuarantine.ifPresent(selfQuarantine -> repository.delete(selfQuarantine));
		return repository.findAll();
	}

}
