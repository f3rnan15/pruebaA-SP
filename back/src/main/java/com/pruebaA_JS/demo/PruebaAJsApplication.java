package com.pruebaA_JS.demo;

import com.pruebaA_JS.demo.entities.Users;
import com.pruebaA_JS.demo.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PruebaAJsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaAJsApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UsersRepository usersRepository) {
		return args -> {
			if (!usersRepository.existsByEmail("admin@example.com")) {
				Users admin = new Users();
				admin.setEmail("admin@example.com");
				admin.setFirstName("admin");
				admin.setLastName("admin");
				admin.setUserPassword("admin"); // idealmente encriptado
				usersRepository.save(admin);
			}
		};
	}
}
