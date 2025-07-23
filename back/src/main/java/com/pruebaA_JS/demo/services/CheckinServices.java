package com.pruebaA_JS.demo.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaA_JS.demo.entities.Checkin;
import com.pruebaA_JS.demo.repository.CheckinRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CheckinServices {
    
	@Autowired
	private CheckinRepository checkinRepository;
	
	public Checkin findById(Long check_id){
		return new Checkin();
	}
}
