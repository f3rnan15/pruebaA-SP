package com.pruebaA_JS.demo.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.time.ZoneId;

import com.pruebaA_JS.demo.entities.Users;
import com.pruebaA_JS.demo.repository.UsersRepository;
import org.hibernate.cfg.SchemaToolingSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaA_JS.demo.entities.Checkin;
import com.pruebaA_JS.demo.repository.CheckinRepository;
import com.pruebaA_JS.demo.dtos.CheckinUpdateDTO;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CheckinServices {
    
	@Autowired
	private CheckinRepository checkinRepository;

	@Autowired
	private UsersRepository userRepository;



	public Checkin createCheckin(Long userId) throws Exception {
		Users user = userRepository.findByUserId(userId)
				.orElseThrow(() -> new Exception("Usuario no encontrado"));


		Optional<Checkin> lastCheckinOpt = checkinRepository.findTopByUserOrderByTimestampDesc(user);

		boolean newIsInside = true;


		if (lastCheckinOpt.isPresent()) {
			newIsInside = !lastCheckinOpt.get().isInside(); // Invertir el valor anterior
		}

		Checkin checkin = new Checkin();
		checkin.setUser(user);
		checkin.setInside(newIsInside);
		checkin.setTimestamp(LocalDateTime.now());

		return checkinRepository.save(checkin);
	}

	public List<Checkin> getCheckinsByDate(LocalDate date, Long userId) {
		LocalDateTime startOfDay = date.atStartOfDay();
		LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
		return checkinRepository.findCheckins(userId,startOfDay, endOfDay);
	}

	public Optional<Checkin> updateCheckin(Long id, CheckinUpdateDTO updateDTO) {
		Optional<Checkin> optional = checkinRepository.findById(id);
		if (!optional.isPresent()) {
			return Optional.empty();
		}

		Checkin checkin = optional.get();

		try {
			System.out.println("timestamp class: " + updateDTO.getTimestamp().getClass().getName());
			LocalDateTime localDateTime = LocalDateTime.parse(updateDTO.getTimestamp());
			checkin.setTimestamp(localDateTime);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Formato de fecha inválido. Usa el formato ISO-8601: yyyy-MM-dd'T'HH:mm:ss");
		}

		checkin.setInside(updateDTO.isInside());
		checkinRepository.save(checkin);
		return Optional.of(checkin);
	}






}
