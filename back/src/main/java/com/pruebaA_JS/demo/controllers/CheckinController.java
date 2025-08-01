package com.pruebaA_JS.demo.controllers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

import com.example.api.DefaultApi;
import com.example.model.CheckinDTO;
import com.pruebaA_JS.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.pruebaA_JS.demo.entities.Checkin;
import com.pruebaA_JS.demo.services.CheckinServices;

@RestController
@RequestMapping("/checkin")
public class CheckinController implements DefaultApi {

	@Autowired
	private CheckinServices checkinService;

	@Autowired
	private UsersController usersController;




	@Override
	public ResponseEntity<CheckinDTO> createCheckin() throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Checkin checkin = checkinService.createCheckin(username);

		CheckinDTO dto = new CheckinDTO();
		dto.setId(checkin.getCheckId());
		dto.setTimestamp(OffsetDateTime.from(checkin.getTimestamp()));
		dto.setInside(checkin.isInside());
		dto.setUser(UserMapper.toDto(checkin.getUser()));


		return ResponseEntity.ok(dto);
	}


	@Override
	public ResponseEntity<List<CheckinDTO>> getCheckinsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<Checkin> checkins = checkinService.getCheckinsByDate(date);

		return ResponseEntity.ok(checkins);
	}

	public ResponseEntity<String> getActiveTimeByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
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

		return ResponseEntity.ok(String.format("%02d:%02d:%02d", hours, minutes, seconds));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Checkin> updateCheckin(@PathVariable Long id, @RequestBody Checkin updatedCheckin) {
		return checkinService.updateCheckin(id, updatedCheckin)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}


}
