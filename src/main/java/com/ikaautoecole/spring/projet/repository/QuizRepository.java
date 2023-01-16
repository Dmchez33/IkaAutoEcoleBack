package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
