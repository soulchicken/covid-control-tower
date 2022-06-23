package com.dev.covid.controller;

import com.dev.covid.model.InfectionTracking;
import com.dev.covid.service.InfectionTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("infectiontracking")
public class InfectionTrackingController {
    @Autowired
    private InfectionTrackingService infectionTrackingService;

    @GetMapping
    public List<InfectionTracking> findAll() {
        return infectionTrackingService.findAll();
    }

    @PostMapping
    public InfectionTracking save(@RequestBody InfectionTracking infectionTracking) {
        return infectionTrackingService.save(infectionTracking);
    }

    @PutMapping
    public List<InfectionTracking> update(@RequestBody InfectionTracking infectionTracking) {
        return infectionTrackingService.update(infectionTracking);
    }

    @DeleteMapping("/{id}")
    public List<InfectionTracking> delete(@PathVariable("id") Long id) {
        return infectionTrackingService.delete(id);
    }

}
