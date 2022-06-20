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
import org.springframework.web.bind.annotation.RestController;

import com.dev.covid.model.Manager;
import com.dev.covid.service.ManagerService;

@RestController
@RequestMapping("manager")
public class ManagerController {
	
	@Autowired
	private ManagerService service;
	
	@GetMapping
	public List<Manager> findAll(){
		return service.findAll();
	}
	
	@PostMapping
	public Manager save(@RequestBody Manager manager) {
		return service.save(manager);
	}
	
	@PutMapping
	public List<Manager> put(@RequestBody Manager manager) {
		return service.put(manager);
	}
	
	@DeleteMapping("/{id}")
	public List<Manager> delete(@PathVariable("id") Long id) {
		return service.delete(id);
	}
	
	
}
