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
        private String status;

        @ManyToOne
        Autoecole autoecole;

        @ManyToOne
        @JoinColumn(name = "idquiz")
        private Quiz quiz;
        public Apprenant(String admin, String s, String encode) {
                super(admin,s,encode);
        }
}
