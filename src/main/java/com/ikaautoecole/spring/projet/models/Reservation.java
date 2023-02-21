package com.ikaautoecole.spring.projet.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime heure;

    @Enumerated(EnumType.STRING)
    private EtatReservation etat;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;


    @ManyToOne
    @JoinColumn(name = "auto_ecole_id")
    private Autoecole autoEcole;

}
