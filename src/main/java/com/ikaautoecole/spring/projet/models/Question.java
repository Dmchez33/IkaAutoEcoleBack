package com.ikaautoecole.spring.projet.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    private String bonnereponse;

    @ManyToOne
    Quiz quiz;
}
