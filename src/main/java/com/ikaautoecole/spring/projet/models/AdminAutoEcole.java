package com.ikaautoecole.spring.projet.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
public class AdminAutoEcole extends Utilisateur {
    private String datenaissance;
    private String lieuxnaissance;
    private String image;
    private String nomautoecole;
    public AdminAutoEcole(String admin, String s, String encode, String datenaissance, String lieuxnaissance, String nomautoecole) {
        super(admin,s,encode);
        this.datenaissance = datenaissance;
        this.lieuxnaissance = lieuxnaissance;
        this.nomautoecole = nomautoecole;
    }
}
