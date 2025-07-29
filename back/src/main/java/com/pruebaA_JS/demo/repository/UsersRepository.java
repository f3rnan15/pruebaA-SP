package com.pruebaA_JS.demo.repository;

import com.pruebaA_JS.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Override
    Optional<Users> findById(Long aLong);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByFirstName(String name);
    Optional<Users> findByUserId(Long userId);
}
