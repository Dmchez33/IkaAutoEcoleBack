package com.ikaautoecole.spring.projet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@SelectBeforeUpdate
@DynamicUpdate
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
        @ManyToOne
        private Quiz quiz;
        public Apprenant(String admin, String s, String encode) {
                super(admin,s,encode);
        }

}
