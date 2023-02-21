package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.PanneauxConduite;
import com.ikaautoecole.spring.projet.models.TypePanneaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PanneauxConduiteRepository extends JpaRepository<PanneauxConduite, Long> {

    PanneauxConduite findByNom(String nom);

    List<PanneauxConduite> findByTypePanneaux(TypePanneaux typePanneaux);
}
