package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.Apprenant;
import com.ikaautoecole.spring.projet.repository.ApprenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenantServiceImpl{
    @Autowired
    ApprenantRepository apprenantRepository;

    public Apprenant saveApprenant(Apprenant apprenant)
    {
        return apprenantRepository.save(apprenant);
    }

    public List<Apprenant> getAll(){
        return apprenantRepository.findAll();

    }

    public Apprenant update(Long id, Apprenant apprenant){
        Apprenant apprenant1 = apprenantRepository.findById(id).get();
        if (apprenant1 != null){
            apprenant1.setNom(apprenant.getNom());
            apprenant1.setPrenom(apprenant.getPrenom());
            apprenant1.setDateinscription(apprenant1.getDateinscription());
            apprenant1.setTelephone(apprenant.getTelephone());
            apprenant1.setEmail(apprenant.getEmail());
        }
        return apprenantRepository.save(apprenant1);
    }

    public String delete(Long id){
        Apprenant apprenant = apprenantRepository.getReferenceById(id);
        if (apprenant != null){
            apprenantRepository.delete(apprenant);
            return "Apprenant suprimer avec succes";
        }else {
            return "Apprenant introuvable";
        }
    }

}
