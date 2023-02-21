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
@CrossOrigin(origins = "*", maxAge = 3600)
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
            return ResponseEntity.badRequest().body(new MessageResponse(" ce nom d'utilisateur existe deja!"));
        }

        //VERIFICATION DE L'EXISTANCE DE L'EMAIL
        if (apprenantRepository.existsByEmail(apprenant.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Cet email existe deja!"));
        }

        if (apprenant.getEmail() == ""){
            return ResponseEntity.badRequest().body(new MessageResponse("Email ne doit pas être vide!"));
        }

        if (apprenant.getTelephone() == ""){
            return ResponseEntity.badRequest().body(new MessageResponse("Le numero ne doit pas être vide!"));
        }
        apprenant.setDateinscription(new Date());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName(ERole.ROLE_APPRENANT);
        roles.add(role);
        apprenant.setRoles(roles);
        apprenant.setPassword(encoder.encode(apprenant.getPassword()));
        apprenantService.saveApprenant(apprenant);
        return ResponseEntity.ok().body( new MessageResponse("Ok"));
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
