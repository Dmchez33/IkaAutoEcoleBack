package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.Cours;
import com.ikaautoecole.spring.projet.models.TypeCours;
import com.ikaautoecole.spring.projet.repository.TypeCoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCoursServiceImpl {

    @Autowired
    TypeCoursRepository typeCoursRepository;

    public String saveCours(TypeCours typeCours){
        typeCoursRepository.save(typeCours);
        return "COURS AJOUTER AVEC SUCCESS";
    }

    public List<TypeCours> getAllTypeCours(){
        return typeCoursRepository.findAll();
    }

}
