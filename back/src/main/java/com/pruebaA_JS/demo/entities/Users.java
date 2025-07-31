package com.pruebaA_JS.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
  
  // Esto mapea a la columna SQL
    private String firstName;    // Este es el nombre del atributo en Java


    private String lastName;
    private String email;
    private String userPassword;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Checkin> checkins;
}
