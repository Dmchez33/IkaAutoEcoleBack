package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.ContenuCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursContenuRepository extends JpaRepository<ContenuCours,Long> {
}
