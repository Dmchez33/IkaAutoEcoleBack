package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.ContenuCours;
import com.ikaautoecole.spring.projet.models.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursContenuRepository extends JpaRepository<ContenuCours,Long> {

    List<ContenuCours>  findContenuCoursByCours (Cours cours);
}
