package com.pruebaA_JS.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import javax.swing.text.StyledEditorKit;
import java.text.DateFormat;
import java.text.DecimalFormat;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedules {
    @Id
    private Long id_shedule; // Cambiado a Long para que funcione bien con GenerationType.IDENTITY

    private String descriptiion;

    private DateFormat start_date;

    private DateFormat end_date;

    private DecimalFormat daily_hours;

    private Boolean enabled;

}
