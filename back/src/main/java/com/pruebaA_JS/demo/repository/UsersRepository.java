package com.pruebaA_JS.demo.repository;

import com.pruebaA_JS.demo.entities.Checkin;
import com.pruebaA_JS.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Users findByEmail(String email);
}
