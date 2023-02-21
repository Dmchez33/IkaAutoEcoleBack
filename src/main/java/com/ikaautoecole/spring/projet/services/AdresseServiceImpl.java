package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.Adresse;
import com.ikaautoecole.spring.projet.models.Autoecole;
import com.ikaautoecole.spring.projet.repository.AdresseRepository;
import com.ikaautoecole.spring.projet.repository.AutoEcoleRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class AdresseServiceImpl {
    @Autowired
    AdresseRepository adresseRepository;

    @Autowired
    AutoEcoleRepository autoEcoleRepository;

    //METHODE PERMETTANT DE RETOURNER TOUTES L'ADRESSE
    public List<Adresse> getAllAdresse() {
        return adresseRepository.findAll();
    }

    //METHODE PERMETTANT D'ENREGISTRER UNE ADRESSE
    public String saveAdresse(Adresse adresse) {
        //Autoecole autoecole = autoEcoleRepository.getReferenceById(id);
        /*if (autoecole == null){
            return "Cette autoecole n'existe pas";
        }*/
        //adresse.setAutoecole(autoecole);

            adresseRepository.save(adresse);
            return "ADRESSE ENREGISTRER AVEC SUCCES";
        //}
    }

    //METHODE PERMETTANT DE METTRE A JOUR UNE ADRESSE
    @Transactional
    public String upadateAdresse(Long id, Map<String, Object> adresse) {
        Adresse adresse1 = adresseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("YourObject not found"));

        adresse.forEach((k, v) -> {
            BeanWrapper beanWrapper = new BeanWrapperImpl(adresse1);
            beanWrapper.setPropertyValue(k, v);
        });


        adresseRepository.save(adresse1);
        return "ADRESSE MODIFIER AVEC SUCCES";


    }

    //METHODE PERMETTANT DE RETOURNER UNE ADRESSE PAR SON ID
    public Adresse getAdresseById(Long id) {
        return adresseRepository.getReferenceById(id);
    }
}
