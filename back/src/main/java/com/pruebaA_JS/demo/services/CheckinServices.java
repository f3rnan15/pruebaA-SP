package com.pruebaA_JS.demo.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pruebaA_JS.demo.entities.Users;
import com.pruebaA_JS.demo.repository.UsersRepository;
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

	@Autowired
	private UsersRepository userRepository;

	
	public Checkin findById(Long check_id){
		return new Checkin();
	}

	public Checkin createCheckin(Boolean isInside, String username) throws Exception {
		Users user = userRepository.findByFirstName(username)
				.orElseThrow(() -> new Exception("Usuario no encontrado"));


		Optional<Checkin> lastCheckinOpt = checkinRepository.findTopByUserOrderByTimestampDesc(user);

		boolean newIsInside = true;


		if (lastCheckinOpt.isPresent()) {
			newIsInside = !lastCheckinOpt.get().is_inside(); // Invertir el valor anterior
		}


		Checkin checkin = new Checkin();
		checkin.setUser(user);
		checkin.set_inside(newIsInside);

		return checkinRepository.save(checkin);
	}


}
