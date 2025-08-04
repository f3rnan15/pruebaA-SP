package com.pruebaA_JS.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.pruebaA_JS.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pruebaA_JS.demo.entities.Checkin;


@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {

	Optional<Checkin> findById(Long checkId);

	Optional<Checkin> findTopByUserOrderByTimestampDesc(Users user);

	@Query("SELECT c FROM Checkin c WHERE c.user.userId = :userId AND c.timestamp BETWEEN :start AND :end")
	List<Checkin> findCheckins(@Param("userId") Long userId,
							   @Param("start") LocalDateTime start,
							   @Param("end") LocalDateTime end);

}