package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.AdminAutoEcole;
import com.ikaautoecole.spring.projet.models.Autoecole;
import com.ikaautoecole.spring.projet.repository.AutoEcoleRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Service
public class AutoEcoleServiceImpl {

    @Autowired
    AutoEcoleRepository autoEcoleRepository;

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

}
