package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Apprenant;
import com.ikaautoecole.spring.projet.models.Autoecole;
import com.ikaautoecole.spring.projet.models.Reservation;
import com.ikaautoecole.spring.projet.models.TypeCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByApprenantAndAutoEcoleAndTypeCours(Apprenant apprenant, Autoecole autoecole, TypeCours typeCours);


    List<Reservation> findByAutoEcole(Autoecole autoecole);
}
