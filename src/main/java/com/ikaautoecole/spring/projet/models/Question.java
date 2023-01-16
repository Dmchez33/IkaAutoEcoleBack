package com.ikaautoecole.spring.projet.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany
    @JoinTable(
            name = "question_mauvaise_reponse",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "mr_id")
    )
    List<MauvaiseReponse> mauvaiseReponses = new ArrayList<>();
}
