package com.pruebaA_JS.demo.exceptions;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String email) {
        super("Usuario no encontrado con email: " + email);
    }


}
