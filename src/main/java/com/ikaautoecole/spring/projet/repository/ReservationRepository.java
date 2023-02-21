package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Apprenant;
import com.ikaautoecole.spring.projet.models.Autoecole;
import com.ikaautoecole.spring.projet.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByApprenantAndAutoEcole(Apprenant apprenant, Autoecole autoecole);
}
