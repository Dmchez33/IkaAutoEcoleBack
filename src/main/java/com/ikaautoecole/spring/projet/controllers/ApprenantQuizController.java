package com.ikaautoecole.spring.projet.controllers;

import com.ikaautoecole.spring.projet.models.ApprenantQuiz;
import com.ikaautoecole.spring.projet.repository.ApprenantQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/apprenantquiz")
public class ApprenantQuizController {
    @Autowired
    ApprenantQuizRepository apprenantQuizRepository;
    /*@PostMapping("/faire")
    public ResponseEntity<ApprenantQuiz> faireQuiz(@RequestBody ApprenantQuizDto apprenantQuizDto) {
        ApprenantQuiz apprenantQuiz = new ApprenantQuiz();
        apprenantQuiz.setApprenant(apprenantRepository.findById(apprenantQuizDto.getApprenantId()).get());
        apprenantQuiz.setQuiz(quizRepository.findById(apprenantQuizDto.getQuizId()).get());
        apprenantQuiz.setDateQuiz(LocalDateTime.now());
        return new ResponseEntity<>(apprenantQuizRepository.save(apprenantQuiz), HttpStatus.CREATED);
    }*/

}
