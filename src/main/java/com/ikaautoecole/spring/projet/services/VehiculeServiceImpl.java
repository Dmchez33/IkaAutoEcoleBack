package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.Vehicule;
import com.ikaautoecole.spring.projet.repository.VehiculeRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class VehiculeServiceImpl {

    @Autowired
    VehiculeRepository vehiculeRepository;

    //METHODE PERMETTANT DE RETOURNER TOUTES LES VEHICULES
    public List<Vehicule> getAllVehicule(){
        return vehiculeRepository.findAll();
    }

    //ENREGISTRELENT D'UN VEHICULE
    public String AddVehicule(Vehicule vehicule)
    {
        if (vehiculeRepository.existsByMarquevehiculeAndTypevehiculeAndNomvehicule(vehicule.getMarquevehicule(),vehicule.getTypevehicule(),vehicule.getNomvehicule())){
            return "Ce vehicule existe déjà";
        }else{
            vehiculeRepository.save(vehicule);
            return "Vehicule ajouter avec succes";
        }

    }


    //RETOURNER UN VEHICULE PAR SON ID
    public  Vehicule getById(Long id){
        return vehiculeRepository.getReferenceById(id);
    }


    //METTRE A JOUR UN VEHICULE
    @Transactional
    public Vehicule updateVehicule(Long id, Map<String, Object> updates) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ce vehicule n'existe pas"));

        updates.forEach((k, v) -> {
            BeanWrapper beanWrapper = new BeanWrapperImpl(vehicule);
            beanWrapper.setPropertyValue(k, v);
        });
        //sendEmail.MessageDeRetour(yourObject.getEmail(), yourObject.getUsername(), yourObject.getPassword());

        return vehiculeRepository.save(vehicule);
    }
}
