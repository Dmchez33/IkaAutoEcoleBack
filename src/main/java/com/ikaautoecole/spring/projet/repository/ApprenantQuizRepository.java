package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.ApprenantQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenantQuizRepository extends JpaRepository<ApprenantQuiz, Long> {
}
