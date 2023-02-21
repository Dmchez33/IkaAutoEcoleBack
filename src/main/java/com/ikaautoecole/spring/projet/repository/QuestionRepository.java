package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
