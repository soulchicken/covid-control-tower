package com.dev.covid.service;

import com.dev.covid.model.Danger;
import com.dev.covid.repository.DangerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DangerService {
    @Autowired
    private DangerRepository repository;

    public List<Danger> findAll() {
        return repository.findAll();
    }

    public Danger save(Danger danger) {
        return repository.save(danger);
    }

    public List<Danger> update(Danger danger) {
        final Optional<Danger> findDanger = repository.findById(danger.getPatientPeopleId());

        findDanger.ifPresent(newDanger -> {
            newDanger.setDangerCareDate(danger.getDangerCareDate());
            newDanger.setDangerCareRelease(danger.getDangerCareRelease());
            newDanger.setHospitalroomRoomnumber(danger.getHospitalroomRoomnumber());


            repository.save(newDanger);
        });
        return repository.findAll();
    }

    public List<Danger> delete(Long id) {
        final Optional<Danger> findDanger = repository.findById(id);

        findDanger.ifPresent(danger -> {
            // programList : 삭제하고자 하는 엔터티
            repository.delete(danger);
        });

        return repository.findAll();
    }

}