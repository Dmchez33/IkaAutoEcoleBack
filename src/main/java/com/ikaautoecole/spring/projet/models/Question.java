package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String imageQuiz;
    @ManyToOne
    @JoinColumn(name = "question_quiz")
    private Quiz quiz;

    @JsonIgnore
    @OneToMany
    List<Reponse> Reponses = new ArrayList<>();
}
