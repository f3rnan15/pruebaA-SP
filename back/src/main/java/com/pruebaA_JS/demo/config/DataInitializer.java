package com.pruebaA_JS.demo.config;

import com.pruebaA_JS.demo.entities.Schedules;
import com.pruebaA_JS.demo.repository.SchedulesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

        @Bean
        public CommandLineRunner initSchedules(SchedulesRepository scheduleRepository) {
            return args -> {
                if (scheduleRepository.count() == 0) {
                    Schedules summer = new Schedules();
                    summer.setDescription("Summer intensive schedule (8:00 - 15:00)");
                    summer.setStartDate(LocalDate.of(2025, 7, 1));
                    summer.setEndDate(LocalDate.of(2025, 8, 31));
                    summer.setDailyHours(7.0);
                    summer.setEnabled(true);

                    Schedules winter = new Schedules();
                    winter.setDescription("Winter schedule (8:30 - 18:00)");
                    winter.setStartDate(LocalDate.of(2025, 1, 1));
                    winter.setEndDate(LocalDate.of(2025, 6, 30));
                    winter.setDailyHours(8.5);
                    winter.setEnabled(false);

                    scheduleRepository.saveAll(List.of(summer, winter));

                    System.out.println("Horarios iniciales insertados.");
                }
            };
        }
    }


