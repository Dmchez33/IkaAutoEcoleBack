package com.ikaautoecole.spring.projet.controllers;

import com.ikaautoecole.spring.projet.DTO.request.SuperAdminRequest;
import com.ikaautoecole.spring.projet.models.ERole;
import com.ikaautoecole.spring.projet.models.Role;
import com.ikaautoecole.spring.projet.models.SuperAdmin;
import com.ikaautoecole.spring.projet.DTO.response.MessageResponse;
import com.ikaautoecole.spring.projet.repository.RoleRepository;
import com.ikaautoecole.spring.projet.repository.SuperAdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/collaborateur")
public class SuperAdminController {

    private static final Logger Log = LoggerFactory.getLogger(SuperAdminController.class);
    @Autowired
    SuperAdminRepository superAdminRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    //METHODE PERMETTANT DE RECUPERER TOUS LES UTILISATEURS
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SuperAdmin> allUserAccess() {
        Log.info("RECUPERATION DE TOUT LES COLLABORATEUR");
        return superAdminRepository.findAll();
    }

    //METHODE PERMETTANT L'AJOUT D'UN NOUVEAU COLLABORATEUR
    @PostMapping("/createUtilisateur")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> registerUser(@RequestBody SuperAdminRequest signUpRequest) {

        //VERIFICATION DE L'EXISTANCE DU NOM D'UTILISATEUR
        if (superAdminRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur: ce nom d'utilisateur existe deja!"));
        }

        //VERIFICATION DE L'EXISTANCE DE L'EMAIL
        if (superAdminRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Ce email existe deja!"));
        }


        Log.info("CREATION D'UN Utilisateur");

        // CREATION D'UNE INSTANCE DE COLLABORATEUR
        SuperAdmin user = new SuperAdmin(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),"83252448");

        //RECUPERATION DES ROLES DU COLLABORATEUR
        Set<String> strRoles = signUpRequest.getRole();

        //CREATION D'UNE INSTANCE DE ROLE A L'INTERIEUR PERMETTANT DE STOCKER LES DIFFERENTS ENTRER PAR L'ADMIN
        Set<Role> roles = new HashSet<>();

        //VERIFICATION DU ROLE ENTREZ PAR L'UTILISATEUR
        //SI C'EST NULL ON AFFECTE DIRECTEMENT LE ROLE USER A CE COLLABORATEUR
        //SINON RECUPERE C'EST DIFFERENT ROLE ET ON VERIFIE SI CA EXISTE DANS LA BASE DE DONNEE
        // DANS LE CAS CONTRAIRE ON AFFECTE LE ROLE USER A CE COLLABORATEUR
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_APPRENANT);
            roles.add(userRole);
        }  else {
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
            });
        }

        user.setRoles(roles);
        superAdminRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Utilisateur creer avec success!"));
    }

    //METHODE PERMETTANT DE METTRE A JOUR LES INFORMATION D'UN COLLABORATEUR
    @PutMapping("/updateCollaborateur/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SuperAdmin updateCollaborateur(@Valid @RequestBody SuperAdminRequest signUpRequest, @PathVariable("id") Long id){

        Log.info("MODIFICATION D'UN COLLABORATEUR");

        return  superAdminRepository.findById(id).map(

                signUpRequest1 ->{
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

                    return superAdminRepository.save(signUpRequest1);
                }
        ).orElseThrow(() -> new RuntimeException("Utilisateur non trouvéé"));


    }



    //**************************************** METHODE PERMETTANT DE SUPPRIMER LE COLLABORATEUR
    @DeleteMapping("/deleteUtilisateur/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCollaborateur(@PathVariable("id") Long id){

        Log.info("SUPPRESSION D'UN COLLABORATEUR");

        superAdminRepository.deleteById(id);

        return "utilisateur supprimer";

    }


}
