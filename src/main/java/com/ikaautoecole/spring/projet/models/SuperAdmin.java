package com.ikaautoecole.spring.projet.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "adminsysteme",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class SuperAdmin extends Utilisateur {

    private String telephone;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "role1_id"))
    private Set<Role> roles = new HashSet<>();

    public SuperAdmin(String username, String email, String password,String telephone) {
        super(username,email,password);
        this.telephone = telephone;

    }
}
