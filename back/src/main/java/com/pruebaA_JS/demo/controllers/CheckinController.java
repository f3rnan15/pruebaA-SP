package com.pruebaA_JS.demo.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.pruebaA_JS.demo.entities.Checkin;
import com.pruebaA_JS.demo.services.CheckinServices;

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







}
