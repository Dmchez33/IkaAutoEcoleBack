package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.TypePanneaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePanneauxRepository extends JpaRepository<TypePanneaux, Long> {

    TypePanneaux findByType(String type);
}
