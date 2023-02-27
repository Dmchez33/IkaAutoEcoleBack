package com.ikaautoecole.spring.projet.DTO.request;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DemandeCreationCompte{

    private String email;
    private String nom;
    private String prenom;
    private String username;
    private String telephone;
    private String ville;
    private String quartier;
    private String rue;
    private String porte;
    private String nomAutoEcole;
}