package com.ikaautoecole.spring.projet.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ikaautoecole.spring.projet.Configuration.SaveImage;
import com.ikaautoecole.spring.projet.DTO.response.MessageResponse;
import com.ikaautoecole.spring.projet.models.Question;
import com.ikaautoecole.spring.projet.models.Quiz;
import com.ikaautoecole.spring.projet.models.Reponse;
import com.ikaautoecole.spring.projet.repository.QuestionRepository;
import com.ikaautoecole.spring.projet.repository.ReponseRepository;
import com.ikaautoecole.spring.projet.services.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuizController {

    @Autowired
    QuizServiceImpl quizService;

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ReponseRepository reponseRepository;

    @PostMapping("/ajouterQiuz")
    public ResponseEntity<?> postQuiz(@RequestParam("quiz") String quiz, @RequestParam("image") MultipartFile image){
        try {

            Quiz quiz1 = new JsonMapper().readValue(quiz, Quiz.class);
            quiz1.setQuizDate(new Date());
            if (image != null){

                quiz1.setImageName(SaveImage.save("imageQuiz", image, image.getOriginalFilename()));

            }else {
                return ResponseEntity.ok().body(new MessageResponse("VEILLEZ SELECTIONNER UNE IMAGE"));
            }

            quizService.addQuiz(quiz1);
            return ResponseEntity.ok().body(new MessageResponse("Ok"));

        }catch (Exception e){
            return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
        }
    }

    @GetMapping("/getAllQuiz")
    public List<Quiz> getAll(){
        return quizService.getAllQuiz();
    }

    @GetMapping("/getQuizById/{id}")
    public Quiz getQuizById(@PathVariable("id") Long id){
        return quizService.getQuizById(id);
    }

    //METHODE PERMETTANT D'AJOUTER DES QUESTION
    @PostMapping("/ajouterQuestion")
    public ResponseEntity<?> postQuestion(@RequestParam("question") String question, @RequestParam("image") MultipartFile image){
        try {

            Question question1 = new JsonMapper().readValue(question, Question.class);

            if (image != null){

                question1.setImageQuiz(SaveImage.save("imageQuiz", image, image.getOriginalFilename()));

            }else {
                return ResponseEntity.ok().body(new MessageResponse("VEILLEZ SELECTIONNER UNE IMAGE"));
            }

            quizService.addQuestion(question1);
            return ResponseEntity.ok().body(new MessageResponse("Ok"));

        }catch (Exception e){
            return ResponseEntity.ok().body(new MessageResponse("ERREUR LORS DE L'ENVOIE DE DONNEES"));
        }
    }

    @GetMapping("/getAllQuestion")
    public List<Question> getAllQuestion(){
        return quizService.getAllQuestion();
    }

    @GetMapping("/getQuestionById/{id}")
    public Question getQuestionById(@PathVariable("id") Long id){
        return quizService.getQuestion(id);
    }

    @PostMapping("/ajouterReponse")
    public ResponseEntity<?> postQuiz(@RequestParam("reponse") String reponse){
        try {

            Reponse reponse1 = new JsonMapper().readValue(reponse, Reponse.class);

            if(reponse1.getReponse() == ""){
                return ResponseEntity.ok().body(new MessageResponse(" LA REPONSE NE DOIT PAS ETRE VIDE "));
            }

            quizService.addReponse(reponse1);

            return ResponseEntity.ok().body(new MessageResponse("Ok"));

        }catch (Exception e){
            return ResponseEntity.ok().body(new MessageResponse("ERREUR LORS DE L'ENVOIE"));
        }

    }
    @GetMapping("/getAllReponse")
    public List<Reponse> getAllReponse(){
        return quizService.getAllReponse();
    }

    @GetMapping("/getReponseById/{id}")
    public Reponse getReponseById(@PathVariable("id") Long id){
        return quizService.getReponseById(id);
    }

    @GetMapping("/getReponseByIdQuestion/{idQuestion}")
    public List<Reponse> getByIdQuestion(@PathVariable("idQuestion") Long idQuestion){
        Question question = questionRepository.getReferenceById(idQuestion);
        return reponseRepository.findReponseByQuestion(question);
    }

    @DeleteMapping("/deleteReponse/{idreponse}")
    public ResponseEntity<?> deleteReponse(@PathVariable("idreponse") Long idreponse){
        if (reponseRepository.getReferenceById(idreponse) !=null){
            reponseRepository.deleteById(idreponse);
            return ResponseEntity.ok().body(new MessageResponse("Ok"));
        }else {
            return ResponseEntity.ok().body(new MessageResponse("ERREUR LORS DE SUPPRESSION"));
        }

    }

}
