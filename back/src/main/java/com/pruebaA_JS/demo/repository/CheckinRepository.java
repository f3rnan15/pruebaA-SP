package com.pruebaA_JS.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pruebaA_JS.demo.entities.Checkin;


@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
	Checkin findById(int id_checkin);
	
    List<Checkin> findByDate(Date date);

    List<Checkin> findByUser(int id_user);

    List<Checkin> findOnWorking();
}