package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String ville;
    private String quartier;
    private String longitude;
    private String latitude;
    @JsonIgnore
    @ManyToOne
    Autoecole autoecole;

}
