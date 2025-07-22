package com.pruebaA_JS.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String user_password;

    public Users() {}

    public Users(String apellido, String email, Long id_usuario, String nombre, String password) {
        this.last_name = apellido;
        this.email = email;
        this.id = id_usuario;
        this.first_name = nombre;
        this.user_password = password;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContrasenia_hash() {
        return user_password;
    }

    public void setContrasenia_hash(String contrasenia_hash) {
        this.user_password = contrasenia_hash;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users usuarios = (Users) o;
        return id == usuarios.id && Objects.equals(first_name, usuarios.first_name) && Objects.equals(last_name, usuarios.last_name) && Objects.equals(email, usuarios.email) && Objects.equals(user_password, usuarios.user_password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, user_password);
    }

}
