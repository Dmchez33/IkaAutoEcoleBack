package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
        private String nom;
        private String prenom;
        private String telephone;
        private Date dateinscription;

        @ManyToOne
        Autoecole autoecole;

        @JsonIgnore
        @ManyToMany(fetch = FetchType.LAZY,
                cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE
                })
        @JoinTable(name = "apprenant_quiz",
                joinColumns = { @JoinColumn(name = "apprenant_id") },
                inverseJoinColumns = { @JoinColumn(name = "quiz_id") })
        private List<Quiz> quiz;

        public Apprenant(String admin, String s, String encode) {
                super(admin,s,encode);
        }
}
