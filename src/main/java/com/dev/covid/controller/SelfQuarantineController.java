package com.dev.covid.controller;

import java.util.List;

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
	public List<SelfQuarantine> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public SelfQuarantine findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping("date")
	public List<SelfQuarantine> findByselfQuarantineDateBetween(@RequestParam("start") String start, @RequestParam("end") String end) {
		System.out.println(start);
		System.out.println(end);
		return service.findByselfQuarantineDateBetween(start,end);
	}
	
	@GetMapping("release")
	public List<SelfQuarantine> findByselfQuarantineReleaseBetween(@RequestParam("start") String start, @RequestParam("end") String end) {
		System.out.println(start);
		System.out.println(end);
		return service.findByselfQuarantineReleaseBetween(start,end);
	}
	
	@PostMapping
	public SelfQuarantine save(@RequestBody SelfQuarantine selfQuarantine) {
		return service.save(selfQuarantine);
	}
	
	@PutMapping
	public List<SelfQuarantine> put(@RequestBody SelfQuarantine selfQuarantine) {
		return service.put(selfQuarantine);
	}
	
	@DeleteMapping("/{id}")
	public List<SelfQuarantine> delete(@PathVariable("id") Long id) {
		return service.delete(id);
	}
	
}
