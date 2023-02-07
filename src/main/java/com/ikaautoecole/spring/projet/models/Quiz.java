package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private int nombreQuestion;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "quiz",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List<Apprenant> apprenant;

    @OneToMany
    List<Question> questions = new ArrayList<>();

}
