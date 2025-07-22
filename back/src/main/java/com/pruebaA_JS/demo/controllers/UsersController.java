package com.pruebaA_JS.demo.controllers;

import com.pruebaA_JS.demo.entities.Users;
import com.pruebaA_JS.demo.services.UsersService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping()
    public void registerUser(@ModelAttribute Users user) {
        usersService.addUser(user);
    }
}
