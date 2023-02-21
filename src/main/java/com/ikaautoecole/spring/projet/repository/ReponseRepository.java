package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Question;
import com.ikaautoecole.spring.projet.models.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReponseRepository extends JpaRepository<Reponse, Long> {

    List<Reponse> findReponseByQuestion(Question question);
}
