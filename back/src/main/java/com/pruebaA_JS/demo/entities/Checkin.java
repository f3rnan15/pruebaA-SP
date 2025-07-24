package com.pruebaA_JS.demo.entities;

import java.sql.Date;
import java.time.LocalDateTime;

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
    private Users user;
    private LocalDateTime timestamp;
    private boolean inside;



    
}
