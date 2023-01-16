package com.ikaautoecole.spring.projet.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TypeCours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nomcours;
    private String image;
}
