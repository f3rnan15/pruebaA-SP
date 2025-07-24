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
    private String firstName;
    private String lastName;
    private String email;
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private Collection<Checkin> checkins;
}
