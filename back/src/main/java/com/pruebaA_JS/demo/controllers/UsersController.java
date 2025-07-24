package com.pruebaA_JS.demo.controllers;

import com.pruebaA_JS.demo.entities.Users;
import com.pruebaA_JS.demo.services.UsersService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public Users getUser(@RequestParam Long userId) {
        return usersService.getUser(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
