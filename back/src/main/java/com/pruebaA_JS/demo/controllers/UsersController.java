package com.pruebaA_JS.demo.controllers;

import com.pruebaA_JS.demo.entities.Users;
import com.pruebaA_JS.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/new")
    public void registerUser(@ModelAttribute Users user) {
        try{
            usersService.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/me")
    public String getLoggedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return email;
    }

    @GetMapping()
    public Users getUser(@RequestParam Long userId) {
        return usersService.getUser(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @GetMapping("/{email}")
    public Optional<Users> getUserByEmail(@PathVariable String email) {
        System.out.println(email);
        return usersService.getUserWithEmail(email);
    }

}
