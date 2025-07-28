package com.pruebaA_JS.demo.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference

    private Users user;
    private LocalDateTime timestamp;
    private boolean isInside;

    
}
