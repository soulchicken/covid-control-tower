package com.dev.covid.controller;

import com.dev.covid.model.Danger;
import com.dev.covid.service.DangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("danger")
public class DangerController {

    @Autowired
    private DangerService dangerService;

    @GetMapping
    public List<Danger> findAll() {
        return dangerService.findAll();
    }

    @PostMapping
    public Danger save(@RequestBody Danger danger) {
        return dangerService.save(danger);
    }

    @PutMapping
    public List<Danger> update(@RequestBody Danger danger) {
        return dangerService.update(danger);
    }

    @DeleteMapping("/{id}")
    public List<Danger> delete(@PathVariable("id") Long id) {
        return dangerService.delete(id);
    }
}
