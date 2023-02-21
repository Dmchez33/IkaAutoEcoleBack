package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.PanneauxConduite;
import com.ikaautoecole.spring.projet.models.TypePanneaux;
import com.ikaautoecole.spring.projet.repository.PanneauxConduiteRepository;
import com.ikaautoecole.spring.projet.repository.TypePanneauxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanneauxServiceImpl {

    @Autowired
    PanneauxConduiteRepository panneauxConduiteRepository;

    @Autowired
    TypePanneauxRepository typePanneauxRepository;

    public PanneauxConduite CreatePanneaux(PanneauxConduite panneauxConduite){
        return panneauxConduiteRepository.save(panneauxConduite);
    }

    public List<PanneauxConduite> getAllPanneaux(){
        return panneauxConduiteRepository.findAll();
    }

    public PanneauxConduite getPanneauxByID(Long id){
        return panneauxConduiteRepository.findById(id).get();
    }

    public PanneauxConduite getByNomPanneau(String nom){
        return panneauxConduiteRepository.findByNom(nom);
    }

    //LES METHODE UTILISER POUR LES PANNEAUX
    public TypePanneaux addPanneaux(TypePanneaux typePanneaux){
        return typePanneauxRepository.save(typePanneaux);
    }

    public List<TypePanneaux> getTypePanneaux(){
        return typePanneauxRepository.findAll();
    }


}
