package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Adresse(String ville, String quartier, String longitude, String latitude){
        this.ville = ville;
        this.quartier = quartier;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
