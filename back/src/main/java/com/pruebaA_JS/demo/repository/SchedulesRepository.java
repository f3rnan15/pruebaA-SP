package com.pruebaA_JS.demo.repository;

import com.pruebaA_JS.demo.entities.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.DecimalFormat;
import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedules, Long> {

}
