package com.pruebaA_JS.demo.entities;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;
    private Date timestamp;
    private boolean isInside;

    public Checkin(){}

    public Checkin(Long id_checkin, Users user, Date date, boolean working){
        this.checkId = id_checkin;
        this.user = user;
        this.timestamp = date;
        this.isInside = working;
    }

    
}
