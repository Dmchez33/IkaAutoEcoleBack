package com.ikaautoecole.spring.projet.repository;

import com.ikaautoecole.spring.projet.models.TypeCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCoursRepository extends JpaRepository<TypeCours,Long> {
}
