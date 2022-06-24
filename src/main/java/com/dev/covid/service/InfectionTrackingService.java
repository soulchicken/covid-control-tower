package com.dev.covid.service;


import com.dev.covid.model.InfectionTracking;
import com.dev.covid.repository.InfectionTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfectionTrackingService {

    @Autowired
    private InfectionTrackingRepository repository;

    public List<InfectionTracking> findAll() {
        return repository.findAll();
    }

    public InfectionTracking save(InfectionTracking infectionTracking) {
        return repository.save(infectionTracking);
    }

    public List<InfectionTracking> update(InfectionTracking infectionTracking) {
        final Optional<InfectionTracking> findInfectionTracking = repository.findById(infectionTracking.getInfectionTrackingId());

        findInfectionTracking.ifPresent(newInfectionTracking -> {
            newInfectionTracking.setInfectionTrackingName(infectionTracking.getInfectionTrackingName());
            newInfectionTracking.setInfectionTrackingArea(infectionTracking.getInfectionTrackingArea());
            newInfectionTracking.setInfectionTrackingDate(infectionTracking.getInfectionTrackingDate());
            newInfectionTracking.setInfectionTrackingCause(infectionTracking.getInfectionTrackingCause());

            repository.save(newInfectionTracking);
        });
        return repository.findAll();
    }

    public List<InfectionTracking> delete(Long id) {
        final Optional<InfectionTracking> findInfectionTracking = repository.findById(id);

        findInfectionTracking.ifPresent(infectionTracking -> {
            // programList : 삭제하고자 하는 엔터티
            repository.delete(infectionTracking);
        });

        return repository.findAll();
    }

}
