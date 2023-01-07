package com.ikaautoecole.spring.projet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikaautoecole.spring.projet.models.Collaborateur;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
  Optional<Collaborateur> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
