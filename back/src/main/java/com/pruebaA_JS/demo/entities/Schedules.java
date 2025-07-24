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
    private Long idSchedule; // Cambiado a Long para que funcione bien con GenerationType.IDENTITY

    private String description;

    private DateFormat startDate;

    private DateFormat endDate;

    private DecimalFormat dailyHours;

    private Boolean enabled;

}
