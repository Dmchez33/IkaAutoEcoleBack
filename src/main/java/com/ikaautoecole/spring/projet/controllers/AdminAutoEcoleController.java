package com.ikaautoecole.spring.projet.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ikaautoecole.spring.projet.Configuration.SMSService;
import com.ikaautoecole.spring.projet.Configuration.SaveImage;
import com.ikaautoecole.spring.projet.Configuration.SendEmail;
import com.ikaautoecole.spring.projet.DTO.request.AdminAutoEcoleRequest;
import com.ikaautoecole.spring.projet.DTO.response.MessageResponse;
import com.ikaautoecole.spring.projet.models.AdminAutoEcole;
import com.ikaautoecole.spring.projet.models.ERole;
import com.ikaautoecole.spring.projet.models.Role;
import com.ikaautoecole.spring.projet.models.TypeCours;
import com.ikaautoecole.spring.projet.repository.AdminautoecoleRepository;
import com.ikaautoecole.spring.projet.repository.RoleRepository;
import com.ikaautoecole.spring.projet.repository.TypeCoursRepository;
import com.ikaautoecole.spring.projet.services.AdminAutoEcoleServiceImpl;
import com.ikaautoecole.spring.projet.services.TypeCoursServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/adminAutoEcole")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminAutoEcoleController {

    private static final Logger Log = LoggerFactory.getLogger(AdminAutoEcoleController.class);
    @Autowired
    AdminautoecoleRepository adminautoecoleRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    SendEmail sendEmail;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AdminAutoEcoleServiceImpl adminAutoEcoleDetailsService;

    @Autowired
    private SMSService smsService;

    @Autowired
    TypeCoursServiceImpl typeCoursService;

    //METHODE PERMETTANT DE RECUPERER TOUS LES UTILISATEURS
    @GetMapping("/getAll")
    //@PreAuthorize("hasRole('ADMIN')")
    public List<AdminAutoEcole> get() {
        Log.info("RECUPERATION DE TOUT LES COLLABORATEUR");
        return adminautoecoleRepository.findAll();
    }

    //METHODE PERMETTANT L'AJOUT D'UN NOUVEAU COLLABORATEUR
    @PostMapping("/createUtilisateur")
    //@PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> create(@RequestParam(value = "data") String signUpRequest1, @RequestParam(value = "file", required = false) MultipartFile file) {

        try {
            AdminAutoEcoleRequest signUpRequest = new JsonMapper().readValue(signUpRequest1, AdminAutoEcoleRequest.class);
//VERIFICATION DE L'EXISTANCE DU NOM D'UTILISATEUR
            if (adminautoecoleRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erreur: ce nom d'utilisateur existe deja!"));
            }

            //VERIFICATION DE L'EXISTANCE DE L'EMAIL
            if (adminautoecoleRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Cet email existe deja!"));
            }


            Log.info("CREATION D'UN Utilisateur");

            // CREATION D'UNE INSTANCE DE COLLABORATEUR
            AdminAutoEcole user = new AdminAutoEcole(signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            //RECUPERATION DES ROLES DU COLLABORATEUR
            Set<String> strRoles = signUpRequest.getRole();

            //CREATION D'UNE INSTANCE DE ROLE A L'INTERIEUR PERMETTANT DE STOCKER LES DIFFERENTS ENTRER PAR L'ADMIN
            Set<Role> roles = new HashSet<>();

            //VERIFICATION DU ROLE ENTREZ PAR L'UTILISATEUR
            //SI C'EST NULL ON AFFECTE DIRECTEMENT LE ROLE USER A CE COLLABORATEUR
            //SINON RECUPERE C'EST DIFFERENT ROLE ET ON VERIFIE SI CA EXISTE DANS LA BASE DE DONNEE
            // DANS LE CAS CONTRAIRE ON AFFECTE LE ROLE USER A CE COLLABORATEUR
            if (strRoles == null) {
                Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN_AUTOECOLE);
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN_AUTOECOLE);
                            roles.add(userRole);
                    }
                });
            }

            if (file != null) {

                user.setImage(SaveImage.save("activite", file, user.getUsername()));
            }

            user.setRoles(roles);
            user.setEtat(false);
            user.setNom(signUpRequest.getNom());
            user.setPrenom(signUpRequest.getPrenom());
            user.setTelephone(signUpRequest.getTelephone());
            sendEmail.sendWelcomeEmail(signUpRequest.getEmail(), signUpRequest.getUsername());
            smsService.sendSMS(signUpRequest.getTelephone(), "Votre inscription a été effectué avec success");
            adminautoecoleRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Ok"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse("Erreur lors de creation de l'admin"));
        }
    }

    //METHODE PERMETTANT DE METTRE A JOUR LES INFORMATION D'UN COLLABORATEUR
    @PutMapping("/updateAdminAutoEcole/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminAutoEcole update(@Valid @RequestBody AdminAutoEcoleRequest signUpRequest, @PathVariable("id") Long id) {

        Log.info("MODIFICATION D'UN COLLABORATEUR");

        return adminautoecoleRepository.findById(id).map(
                signUpRequest1 -> {
                    signUpRequest1.setUsername(signUpRequest.getUsername());
                    signUpRequest1.setPassword(encoder.encode(signUpRequest.getPassword()));
                    signUpRequest1.setEmail(signUpRequest.getEmail());
                    Set<String> strRoles = signUpRequest.getRole();
                    Set<Role> roles = new HashSet<>();

                    //RECUPERATION DES ROLES ET VERIFICATION DANS LA BASE DE DONNEE
                    strRoles.forEach(role -> {

                                switch (role) {
                                    case "admin":
                                        Role adminRole = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
                                        roles.add(adminRole);
                                        break;
                                    default:
                                        Role userRole = roleRepository.findByName(ERole.ROLE_APPRENANT);
                                        roles.add(userRole);
                                }
                            }
                    );
                    signUpRequest1.setRoles(roles);


                    return adminautoecoleRepository.save(signUpRequest1);
                }
        ).orElseThrow(() -> new RuntimeException("Utilisateur non trouvéé"));


    }


    //**************************************** METHODE PERMETTANT DE SUPPRIMER LE COLLABORATEUR
    @DeleteMapping("/deleteUtilisateur/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") Long id) {

        Log.info("SUPPRESSION D'UN COLLABORATEUR");

        adminautoecoleRepository.deleteById(id);

        return "utilisateur supprimer";

    }

    @GetMapping("/getById/{id}")
    public AdminAutoEcole getById(@PathVariable("id") Long id){
        return adminautoecoleRepository.getReferenceById(id);
    }

    //+++++++++++++++++++++++++++++++++++++++METHODE PERMETTANT D'ACTIVER LE COMPTE D'UN UTILSATEUR +++++++++++++++++++++++++++++++++++++++++++++++++
    @PatchMapping("/update/{id}")
    public AdminAutoEcole updateEtat(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates) {
        return adminAutoEcoleDetailsService.updatePartial(id, updates);
    }



    //**************************************METHODE CONCERNANT LA RESERVATION DES COURS AU PRES DES AUTOECOLE

}
