package com.ikaautoecole.spring.projet.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class VideoForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;

    @ManyToOne
    Autoecole autoecole;
}