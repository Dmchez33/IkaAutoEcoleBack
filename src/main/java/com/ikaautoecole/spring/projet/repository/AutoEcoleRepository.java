package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.AdminAutoEcole;
import com.ikaautoecole.spring.projet.models.Adresse;
import com.ikaautoecole.spring.projet.models.Autoecole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoEcoleRepository extends JpaRepository<Autoecole, Long> {
    //Boolean existsByAdressesAndNom(List<Adresse> adresse, String nom);

    List<Autoecole> findAutoecoleByAdresses(Adresse adresse);
    Autoecole  findByRue(String rue);
    Autoecole findByPorte(String porte);
    //LisAutoecole findByAdresses(List<Adresse> adresses);
    List<Autoecole> findByAdminAutoEcole(AdminAutoEcole adminAutoEcole);
}
