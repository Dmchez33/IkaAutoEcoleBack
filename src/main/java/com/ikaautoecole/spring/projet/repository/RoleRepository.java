package com.ikaautoecole.spring.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikaautoecole.spring.projet.models.ERole;
import com.ikaautoecole.spring.projet.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(ERole name);
}
