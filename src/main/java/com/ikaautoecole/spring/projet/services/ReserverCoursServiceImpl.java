package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.Reservation;
import com.ikaautoecole.spring.projet.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserverCoursServiceImpl {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation faireReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id){
        return reservationRepository.getReferenceById(id);
    }
}
