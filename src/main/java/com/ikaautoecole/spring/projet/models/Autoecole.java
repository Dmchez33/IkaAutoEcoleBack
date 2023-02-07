package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "autoecole_adresse",
            joinColumns = @JoinColumn(name = "autoecole_id"),
            inverseJoinColumns = @JoinColumn(name = "adresse_id"))
    List<Adresse> adresses = new ArrayList<>();

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "autoecole_vehicule",
            joinColumns = @JoinColumn(name = "autoecole_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicule_id"))
    List<Vehicule> vehicules = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "autoecole_type_cours",
            joinColumns = @JoinColumn(name = "autoecole_id"),
            inverseJoinColumns = @JoinColumn(name = "typecours_id"))
    List<TypeCours> typeCours = new ArrayList<>();

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "autoecole_apprenant",
            joinColumns = @JoinColumn(name = "autoecole_id"),
            inverseJoinColumns = @JoinColumn(name = "apprenant_id"))
    List<Apprenant> apprenants = new ArrayList<>();

    @ManyToOne
    AdminAutoEcole adminAutoEcole;


}
