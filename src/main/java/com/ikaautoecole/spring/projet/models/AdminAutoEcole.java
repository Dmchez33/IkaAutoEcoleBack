package com.ikaautoecole.spring.projet.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@SelectBeforeUpdate
@DynamicUpdate
public class AdminAutoEcole extends Utilisateur {
    private String datenaissance;
    private String lieuxnaissance;
    private String image;
    private String nomautoecole;
    private String telephone;
    private Boolean etat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role1_id"))
    private Set<Role> roles = new HashSet<>();

    public AdminAutoEcole(String admin, String s, String encode, String datenaissance, String lieuxnaissance, String nomautoecole) {
        super(admin,s,encode);
        this.datenaissance = datenaissance;
        this.lieuxnaissance = lieuxnaissance;
        this.nomautoecole = nomautoecole;
    }
}
