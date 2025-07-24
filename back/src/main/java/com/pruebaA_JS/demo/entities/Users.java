package com.pruebaA_JS.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
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

    @Column(name = "user_id")
    private Long userId;
  
    @Column(name = "first_name") // Esto mapea a la columna SQL
    private String firstName;    // Este es el nombre del atributo en Java

    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private Collection<Checkin> checkins;
}
