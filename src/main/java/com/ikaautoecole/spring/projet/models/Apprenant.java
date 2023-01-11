package com.ikaautoecole.spring.projet.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "apprenant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Apprenant extends Utilisateur {
        private String image;
        private String telephone;


        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "roles",
                joinColumns = @JoinColumn(name = "admin_id"),
                inverseJoinColumns = @JoinColumn(name = "role1_id"))
        private Set<Role> roles = new HashSet<>();
        public Apprenant(String admin, String s, String encode) {
                super(admin,s,encode);
        }
}
