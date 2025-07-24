package com.pruebaA_JS.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "first_name") // Esto mapea a la columna SQL
    private String firstName;    // Este es el nombre del atributo en Java

    private String last_name;
    private String email;
    private String user_password;

}
