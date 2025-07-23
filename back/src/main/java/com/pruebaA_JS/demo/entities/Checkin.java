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
    private Long check_id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;
    private Date timestamp;
    private boolean is_inside;

    public Checkin(){}

    public Checkin(Long id_checkin, Users user, Date date, boolean working){
        this.check_id = id_checkin;
        this.user = user;
        this.timestamp = date;
        this.is_inside = working;
    }

    
}
