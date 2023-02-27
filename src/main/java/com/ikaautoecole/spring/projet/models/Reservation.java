package com.ikaautoecole.spring.projet.models;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@SelectBeforeUpdate
@DynamicUpdate
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime heure;

    @Enumerated(EnumType.STRING)
    private EtatReservation etat;

    @ManyToOne
    @JoinColumn(name = "apprenant_reservation")
    private Apprenant apprenant;

    @ManyToOne
    @JoinColumn(name = "type_cours_reservation")
    private TypeCours typeCours;

    @ManyToOne
    @JoinColumn(name = "auto_ecole_reservation")
    private Autoecole autoEcole;

}
