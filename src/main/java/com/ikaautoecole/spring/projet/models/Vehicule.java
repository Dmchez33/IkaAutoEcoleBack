package com.ikaautoecole.spring.projet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomvehicule;
    private String typevehicule;
    private String marquevehicule;
    private String image;

    @ManyToOne
    Autoecole autoecolee;

}
