package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule,Long> {

    Boolean existsByMarquevehiculeAndTypevehicule(String marque, String type);
}
