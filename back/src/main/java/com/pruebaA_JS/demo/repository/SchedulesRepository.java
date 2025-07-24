package com.pruebaA_JS.demo.repository;

import com.pruebaA_JS.demo.entities.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Long> {

}
