package com.pruebaA_JS.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.pruebaA_JS.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pruebaA_JS.demo.entities.Checkin;


@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {

	Optional<Checkin> findById(Long checkId);

	Optional<Checkin> findTopByUserOrderByTimestampDesc(Users user);

}