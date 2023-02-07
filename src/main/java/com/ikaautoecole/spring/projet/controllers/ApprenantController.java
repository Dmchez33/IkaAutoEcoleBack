package com.ikaautoecole.spring.projet.controllers;

import com.ikaautoecole.spring.projet.DTO.response.MessageResponse;
import com.ikaautoecole.spring.projet.models.Apprenant;
import com.ikaautoecole.spring.projet.models.ERole;
import com.ikaautoecole.spring.projet.models.Role;
import com.ikaautoecole.spring.projet.repository.ApprenantRepository;
import com.ikaautoecole.spring.projet.repository.RoleRepository;
import com.ikaautoecole.spring.projet.services.ApprenantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/apprenant")
public class ApprenantController {


    @Autowired
    ApprenantServiceImpl apprenantService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ApprenantRepository apprenantRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/save")
    public ResponseEntity<?> saveApprenant(@RequestBody Apprenant apprenant){
        if (apprenantRepository.existsByUsername(apprenant.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur: ce nom d'utilisateur existe deja!"));
        }

        //VERIFICATION DE L'EXISTANCE DE L'EMAIL
        if (apprenantRepository.existsByEmail(apprenant.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Cet email existe deja!"));
        }
        apprenant.setDateinscription(new Date());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(ERole.ROLE_APPRENANT);
        roles.add(role);
        apprenant.setRoles(roles);
        apprenant.setPassword(encoder.encode(apprenant.getPassword()));
        return ResponseEntity.ok().body( apprenantService.saveApprenant(apprenant));
    }

    @GetMapping("/getapprenant")
    public List<Apprenant> getApp(){
        return  apprenantService.getAll();
    }

    @PutMapping("/update/{id}")
    public Apprenant updateApp(Long id, @RequestBody Apprenant apprenant){
        return apprenantService.update(id, apprenant);
    }



}
