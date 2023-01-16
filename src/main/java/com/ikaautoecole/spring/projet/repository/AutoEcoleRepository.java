package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Autoecole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoEcoleRepository extends JpaRepository<Autoecole, Long> {
}
