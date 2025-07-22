package com.pruebaA_JS.demo.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_checkin;
    @OneToMany
    @JoinColumn(name = "id_user")
    private int id_user;
    private Date date;
    private boolean working;

    public Checkin(){}

    public Checkin(int id_checkin, int id_user, Date date, boolean working){
        this.id_checkin = id_checkin;
        this.id_user = id_user;
        this.date = date;
        this.working = working;
    }

    
}
