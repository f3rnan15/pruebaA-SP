package com.pruebaA_JS.demo.controllers;

import com.pruebaA_JS.demo.entities.AuthRequest;
import com.pruebaA_JS.demo.entities.AuthResponse;
import com.pruebaA_JS.demo.entities.Users;
import com.pruebaA_JS.demo.services.MyUserDetailsService;
import com.pruebaA_JS.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping("/new")
    public Users registerUser(@RequestBody Users user) {
        try{
            usersService.addUser(user);
            return user;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
        Optional<Users> user = usersService.getUserWithEmail(authRequest.getEmail());

        String email = user.map(Users::getEmail).orElse("Email no disponible");
        Long id = user.map(Users::getUserId).orElse((long) -1);

        final String jwt = jwtUtil.generateToken(email, id);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        try{
            usersService.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final String jwt = jwtUtil.generateToken(user.getEmail(), user.getUserId());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
