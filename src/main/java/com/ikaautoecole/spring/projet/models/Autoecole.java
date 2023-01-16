package com.ikaautoecole.spring.projet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Autoecole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "autoecole_adresse",
            joinColumns = @JoinColumn(name = "autoecole_id"),
            inverseJoinColumns = @JoinColumn(name = "adresse_id"))
    List<Adresse> adresses = new ArrayList<>();

    @OneToMany
    List<Vehicule> vehicules = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "autoecole_type_cours",
            joinColumns = @JoinColumn(name = "autoecole_id"),
            inverseJoinColumns = @JoinColumn(name = "typecours_id"))
    List<TypeCours> typeCours = new ArrayList<>();

    @OneToMany
    List<Apprenant> apprenants = new ArrayList<>();

    @ManyToOne
    AdminAutoEcole adminAutoEcole;


}
