package com.ikaautoecole.spring.projet.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
public class PanneauxConduite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Lob
    private String description;
    private String vocal;
    private String image;

    @ManyToOne
    TypePanneaux typePanneaux;

}
