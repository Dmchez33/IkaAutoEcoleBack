package com.ikaautoecole.spring.projet.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder

public abstract class Utilisateur {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  private Boolean status = false;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "admin_roles",
          joinColumns = @JoinColumn(name = "admin_id"),
          inverseJoinColumns = @JoinColumn(name = "role1_id"))
  private Set<Role> roles = new HashSet<>();


  public Utilisateur(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
