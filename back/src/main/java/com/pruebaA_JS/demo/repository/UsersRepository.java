package com.pruebaA_JS.demo.repository;

import com.pruebaA_JS.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
