package com.ikaautoecole.spring.projet.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String longitude;
    private String latitude;
    private String ville;
    private String quartier;
    private String telephone;


}
