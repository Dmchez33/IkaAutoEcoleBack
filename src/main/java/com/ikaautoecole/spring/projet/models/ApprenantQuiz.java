package com.ikaautoecole.spring.projet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "apprenant_quiz")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprenantQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private int note;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "apprenantQuiz", cascade = CascadeType.ALL)
    private List<Reponse> reponses;

}
