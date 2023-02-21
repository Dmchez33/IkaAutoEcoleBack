package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.Question;
import com.ikaautoecole.spring.projet.models.Quiz;
import com.ikaautoecole.spring.projet.models.Reponse;
import com.ikaautoecole.spring.projet.repository.QuestionRepository;
import com.ikaautoecole.spring.projet.repository.QuizRepository;
import com.ikaautoecole.spring.projet.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ReponseRepository reponseRepository;

    //************************PARTIE CONCERNANT LES QUIZ**********************/
    public Quiz addQuiz(Quiz quiz){
        return quizRepository.save(quiz);

    }

    public List<Quiz> getAllQuiz(){
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long id)
    {
        return quizRepository.getReferenceById(id);
    }


    //************************* LES METHODDE UTILISER POUR LES QUESTION ***********************/

    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public Question getQuestion(Long id){
        return questionRepository.getReferenceById(id);
    }


    //***************************** METHODE UTILISER POUR LES REPONSE ******************/

    public Reponse addReponse(Reponse reponse){
        return reponseRepository.save(reponse);
    }

    public List<Reponse> getAllReponse(){
        return reponseRepository.findAll();
    }

    public Reponse getReponseById(Long id){
        return reponseRepository.getReferenceById(id);
    }

}
