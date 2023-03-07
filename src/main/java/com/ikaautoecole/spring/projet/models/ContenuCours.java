package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ContenuCours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String titre;
    @Lob
    private String description;
    private String vocal;
    private String image;
    @JsonIgnore
    @ManyToOne
    Cours cours;
}
