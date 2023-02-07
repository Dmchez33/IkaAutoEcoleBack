package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String libelle;
    private String image;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "quiz_cours",
            joinColumns = @JoinColumn(name = "cours_id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private List<Quiz> quiz;

    @ManyToMany
    @JoinTable(
            name = "cours_apprenant",
            joinColumns = @JoinColumn(name = "cours_id"),
            inverseJoinColumns = @JoinColumn(name = "apprenant_id")
    )
    List<Apprenant> apprenant = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    List<ContenuCours> contenuCours = new ArrayList<>();
}
