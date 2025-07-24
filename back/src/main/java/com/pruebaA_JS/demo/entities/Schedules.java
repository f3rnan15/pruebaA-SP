package com.pruebaA_JS.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.StyledEditorKit;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId; // Cambiado a Long para que funcione bien con GenerationType.IDENTITY

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Double dailyHours;

    private Boolean enabled;

}
