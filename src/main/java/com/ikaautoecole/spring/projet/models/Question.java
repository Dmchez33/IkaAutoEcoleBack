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
    private String question;

    @ManyToOne
    @JoinColumn(name = "question_quiz")
    private Quiz quiz;

    @ManyToMany
    @JoinTable(
            name = "question_reponse",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "reponse_id")
    )
    List<Reponse> Reponses = new ArrayList<>();
}
