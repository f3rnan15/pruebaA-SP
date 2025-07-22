package com.pruebaA_JS.demo.services;

import com.pruebaA_JS.demo.entities.Schedules;
import com.pruebaA_JS.demo.repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulesService {

    @Autowired
    private SchedulesRepository repository;

    public List<Schedules> findAll(){
        return repository.findAll();
    }

}
