package com.dev.covid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.covid.model.SelfQuarantine;
import com.dev.covid.service.SelfQuarantineService;

@RestController
@RequestMapping("selfquarantine")
public class SelfQuarantineController {
	
	@Autowired
	private SelfQuarantineService service;
	
	@GetMapping
	public List<SelfQuarantine> findAll() {
		return service.findAll();
	}
	
	@PostMapping
	public SelfQuarantine save(@RequestBody SelfQuarantine selfQuarantine) {
		return service.save(selfQuarantine);
	}
}
