package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.AdminAutoEcole;
import com.ikaautoecole.spring.projet.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminautoecoleRepository extends JpaRepository<AdminAutoEcole, Long> {
    Optional<AdminAutoEcole> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
