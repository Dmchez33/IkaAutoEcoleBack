package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.ContenuCours;
import com.ikaautoecole.spring.projet.models.Cours;
import com.ikaautoecole.spring.projet.repository.CoursContenuRepository;
import com.ikaautoecole.spring.projet.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CoursServiceImpl {

    @Autowired
    CoursRepository cours;

    @Autowired
    CoursContenuRepository coursContenuRepository;

    public List<Cours> getCours(){
        return cours.findAll();
    }

    public Cours getCoursById(Long id){
        return cours.getReferenceById(id);
    }

    public Cours saveCours(Cours cours1){
        return cours.save(cours1);
    }

    public List<ContenuCours> getContenuCours(){
        return coursContenuRepository.findAll();
    }

    public ContenuCours getContenuCoursById(Long id){
        return coursContenuRepository.getReferenceById(id);
    }

    public ContenuCours saveContenu(ContenuCours contenuCours){
        return coursContenuRepository.save(contenuCours);
    }



}
