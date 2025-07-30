package com.pruebaA_JS.demo.controllers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.pruebaA_JS.demo.entities.Checkin;
import com.pruebaA_JS.demo.services.CheckinServices;
import com.pruebaA_JS.demo.dtos.CheckinUpdateDTO;

@RestController
@RequestMapping("/checkin")
public class CheckinController {

	@Autowired
	private CheckinServices checkinService;

	@Autowired
	private UsersController usersController;



	@PostMapping("/new")
	public ResponseEntity<Checkin> createCheckin() throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Checkin checkin = checkinService.createCheckin(username);
		return ResponseEntity.ok(checkin);

	}

	@GetMapping("/daysCheckins")
	public ResponseEntity<List<Checkin>> getCheckinsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<Checkin> checkins = checkinService.getCheckinsByDate(date);
		return ResponseEntity.ok(checkins);
	}

	@GetMapping("/active-time")
	public String getActiveTimeByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<Checkin> checkins = checkinService.getCheckinsByDate(date); // Consulta filtrada por fecha
		checkins.sort(Comparator.comparing(Checkin::getTimestamp));

		Duration totalDuration = Duration.ZERO;
		LocalDateTime entryTime = null;
		LocalDateTime now = LocalDateTime.now();

		for (Checkin checkin : checkins) {
			if (checkin.isInside()) {
				entryTime = checkin.getTimestamp();
			} else if (entryTime != null) {
				totalDuration = totalDuration.plus(Duration.between(entryTime, checkin.getTimestamp()));
				entryTime = null;
			}
		}

		// Si sigue dentro y la fecha es hoy, sumar hasta ahora
		if (entryTime != null && date.equals(LocalDate.now())) {
			totalDuration = totalDuration.plus(Duration.between(entryTime, now));
		}


		long hours = totalDuration.toHours();
		long minutes = totalDuration.toMinutes() % 60;
		long seconds = totalDuration.getSeconds() % 60;

		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Checkin> updateCheckin(@PathVariable Long id, @RequestBody CheckinUpdateDTO dto) {
		return checkinService.updateCheckin(id, dto)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}


}
