package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.SuperAdmin;
import com.ikaautoecole.spring.projet.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
