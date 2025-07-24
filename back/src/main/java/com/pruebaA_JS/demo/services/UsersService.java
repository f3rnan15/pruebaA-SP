package com.pruebaA_JS.demo.services;

import com.pruebaA_JS.demo.entities.Users;
import com.pruebaA_JS.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private  UsersRepository usersRepository;

    public void addUser(Users user){usersRepository.save(user);}

    public Optional<Users> getUser(Long userId) {
        return usersRepository.findById(userId);
    }
}
