package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.Configuration.SMSService;
import com.ikaautoecole.spring.projet.models.AdminAutoEcole;
import com.ikaautoecole.spring.projet.models.Autoecole;
import com.ikaautoecole.spring.projet.models.EtatReservation;
import com.ikaautoecole.spring.projet.models.Reservation;
import com.ikaautoecole.spring.projet.repository.AutoEcoleRepository;
import com.ikaautoecole.spring.projet.repository.ReservationRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AutoEcoleServiceImpl {

    @Autowired
    AutoEcoleRepository autoEcoleRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    SMSService smsService;

    public List<Autoecole> getAutoEcole(){
        return autoEcoleRepository.findAll();
    }

    public Autoecole saveAutoEcole(Autoecole autoecole){
        autoecole.setStatus(true);
        return autoEcoleRepository.save(autoecole);
    }

    public String desactiveAutoEcole(Long id){
        Autoecole autoecole = autoEcoleRepository.findById(id).get();
        if (autoecole != null){
            autoecole.setStatus(false);
            autoEcoleRepository.save(autoecole);
            return "Cette auto-Ecole ne fait plus partie de cette adresse";
        }else {
            return "L'auto-Ecole n'existe pas";
        }
    }

    //METHODE PERMETTANT DE METRE A JOUR L'AUTOECOLE
    @Transactional
    public Autoecole updateAutoEcole(Long id, Map<String, Object> autoecole1) {
        Autoecole autoecole = autoEcoleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("YourObject not found"));

        autoecole1.forEach((k, v) -> {
            BeanWrapper beanWrapper = new BeanWrapperImpl(autoecole);
            beanWrapper.setPropertyValue(k, v);
        });
        //sendEmail.MessageDeRetour(yourObject.getEmail(), yourObject.getUsername(), yourObject.getPassword());

        return autoEcoleRepository.save(autoecole);
    }

    //METHODE PERMETTANT DE RECUPERER UNE AUTOECOLE PAR SON ID
    public Autoecole getAutoEcoleById(Long id){
        return autoEcoleRepository.getReferenceById(id);
    }

    //Reservation
    @Transactional
    public Reservation updatePartial(Long id, Map<String, Object> updates) {
        Reservation yourObject = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("YourObject not found"));

        updates.forEach((k, v) -> {
            BeanWrapper beanWrapper = new BeanWrapperImpl(yourObject);
            beanWrapper.setPropertyValue(k, v);
        });
        //sendEmail.MessageDeRetour(yourObject.getEmail(), yourObject.getUsername(), yourObject.getPassword());
        smsService.sendSMSAcceptReserve(yourObject.getApprenant().getTelephone(),yourObject.getApprenant().getPrenom());
        return reservationRepository.save(yourObject);
    }


    public void modifierEtatReservation(Long reservationId, EtatReservation nouvelEtat) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setEtat(nouvelEtat);
            reservationRepository.save(reservation);
            smsService.sendSMSAcceptReserve(reservation.getApprenant().getTelephone(),reservation.getApprenant().getPrenom());

        } else {
            // Gérer le cas où l'ID de réservation fourni n'est pas valide
            throw new IllegalArgumentException("L'ID de réservation fourni n'est pas valide : " + reservationId);
        }
    }
}
