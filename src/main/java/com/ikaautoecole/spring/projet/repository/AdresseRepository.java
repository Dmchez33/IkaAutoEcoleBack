package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {

    //Boolean existsByLatitudeAndLongitude(String Latitude, String Longitude);
    Adresse findByQuartier(String quartier);
}
