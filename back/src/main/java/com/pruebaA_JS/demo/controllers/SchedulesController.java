package com.pruebaA_JS.demo.controllers;

import com.pruebaA_JS.demo.entities.Schedules;
import com.pruebaA_JS.demo.repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class SchedulesController {


    @Autowired
    private SchedulesRepository repository;

    @GetMapping("/schedules")
    public List<Schedules> findAll(){
        return repository.findAll();
    }


}
