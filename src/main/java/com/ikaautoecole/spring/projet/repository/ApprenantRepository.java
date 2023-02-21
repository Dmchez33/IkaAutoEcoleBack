package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
    Optional<Apprenant> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByTelephone(String telephone);
    Boolean existsByEmail(String email);
}
