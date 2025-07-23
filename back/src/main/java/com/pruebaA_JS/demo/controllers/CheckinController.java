package com.pruebaA_JS.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaA_JS.demo.entities.Checkin;
import com.pruebaA_JS.demo.services.CheckinServices;

@RestController
@RequestMapping("/checkin")
public class CheckinController {
	
	private final CheckinServices checkinService;
	
	public CheckinController(CheckinServices checkinService) {
		this.checkinService = checkinService;
	}
	
	public Checkin findCheckinById(@ModelAttribute Long id){
		return checkinService.findById(id);
	}
}
