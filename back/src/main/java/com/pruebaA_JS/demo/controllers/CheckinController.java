package com.pruebaA_JS.demo.controllers;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pruebaA_JS.demo.entities.Checkin;
import com.pruebaA_JS.demo.services.CheckinServices;

@RestController
@RequestMapping("/checkin")
public class CheckinController {

	@Autowired
	private CheckinServices checkinService;
	
	@GetMapping("/chekin")
	public Checkin findCheckinById(@ModelAttribute Long id){
		return checkinService.findById(id);
	}


	@PostMapping
	public ResponseEntity<Checkin> createCheckin() throws Exception {
		String username = "string";
		Checkin checkin = checkinService.createCheckin(username);
		return ResponseEntity.ok(checkin);
	}





}
