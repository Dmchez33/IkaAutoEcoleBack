package com.ikaautoecole.spring.projet.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ikaautoecole.spring.projet.Configuration.SaveImage;
import com.ikaautoecole.spring.projet.Configuration.SendEmail;
import com.ikaautoecole.spring.projet.DTO.response.MessageResponse;
import com.ikaautoecole.spring.projet.models.*;
import com.ikaautoecole.spring.projet.repository.*;
import com.ikaautoecole.spring.projet.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/AutoEcole")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AutoEcoleController {

    @Autowired
    AutoEcoleServiceImpl autoEcoleService;

    @Autowired
    AutoEcoleRepository autoEcoleRepository;

    @Autowired
    AdminautoecoleRepository adminautoecoleRepository;

    @Autowired
    AdresseServiceImpl adresseService;

    @Autowired
    AdresseRepository adresseRepository;

    @Autowired
    VehiculeServiceImpl vehiculeService;

    @Autowired
    TypeCoursServiceImpl typeCoursService;

    @Autowired
    ReserverCoursServiceImpl reserverCoursService;

    @Autowired
    ApprenantRepository apprenantRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    SendEmail sendEmail;

    //RETOURNE TOUTES LES AUTOECOLES
    @GetMapping("/getAll")
    public List<Autoecole> get(){
        return autoEcoleService.getAutoEcole();
    }

    //AJOUTER UNE AUTOECOLE
    @PostMapping("/save/{idadminAuto}")
    public ResponseEntity<?> saveAutoEcole(@RequestBody Autoecole autoecole,@PathVariable("idadminAuto") Long id){

        //RECUPERATION DU NOM DE L'ADMIN DE L'AUTOECOLE
        AdminAutoEcole adminAutoEcole = adminautoecoleRepository.getReferenceById(id);
        if (adminAutoEcole != null)
        {
            autoecole.setAdminAutoEcole(adminAutoEcole);
        }

        if(autoEcoleRepository.findByPorte(autoecole.getPorte()) != null){
            return ResponseEntity.ok().body(new MessageResponse("Ce numéro de porte est déjà prise"));

        }

        if(autoEcoleRepository.findByRue(autoecole.getRue()) != null){
            return ResponseEntity.ok().body(new MessageResponse("Ce numéro de rue est déjà prise"));

        }

        if(autoecole.getPorte() == ""){
            return ResponseEntity.ok().body(new MessageResponse("Veuillez indiquer le numéro de porte."));
 
        }

        if(autoecole.getRue() == ""){
            return ResponseEntity.ok().body(new MessageResponse("Veuillez indiquer le numéro de rue."));
 
        }
        if(autoecole.getNom() == ""){
            return ResponseEntity.ok().body(new MessageResponse("Veuillez indiquer le nomde l'auto-école."));
 
        }
        if(autoecole.getAdresses() == null){
            return ResponseEntity.ok().body(new MessageResponse("Veuillez indiquer l'adresse de l'autoécole."));
 
        }
        /*if (autoEcoleRepository.existsByAdressesAndNom(autoecole.getAdresses(),autoecole.getNom())){
            return ResponseEntity.badRequest().body("Cette adresse est déjà pris");
        }*/autoEcoleService.saveAutoEcole(autoecole);
        return ResponseEntity.ok().body(new MessageResponse("Ok"));
    }

    //METTRE A JOUR UNE AUTOECOLE
    @PatchMapping("/update/{id}")
    public Autoecole updateAutoEcole(@PathVariable("id") Long id, @RequestBody Map<String, Object> autoecole) {
        return autoEcoleService.updateAutoEcole(id, autoecole);
    }

    //RETOURNER UNE AUTOECOLE PAR SON ID
    @GetMapping("/getbyId")
    public Autoecole getbyId(@PathVariable Long id){
        return autoEcoleService.getAutoEcoleById(id);
    }

    //GET AUTOECOLE PAR QUARTIER ADRESSE
    @GetMapping("/getAutoEcole/{quartier}")
    public List<Autoecole> getAutoByQuartier(@PathVariable("quartier") String quartier){
        Adresse adresse = adresseRepository.findByQuartier(quartier);
        if (adresse != null){
            return autoEcoleRepository.findAutoecoleByAdresses(adresse);
        }else {
            return null;
        }
    }

    //OBTENIR TOUTES L'ADRESSE
    @GetMapping("/getallAdresse")
    public List<Adresse> getAllAdresse(){
        return adresseService.getAllAdresse();

    }

    //AJOUTER UNE ADRESSE
    @PostMapping("/addAdresse")
    public ResponseEntity<?> ajouterAdresse(@RequestBody Adresse adresse){
        if(adresseRepository.findByLatitude(adresse.getLatitude())!=null){
            return ResponseEntity.ok().body(new MessageResponse("Cette Latitude existe déjà"));

        }

        if(adresseRepository.findByLongitude(adresse.getLongitude())!=null){
            return ResponseEntity.ok().body(new MessageResponse("Cette longitude existe déjà"));

        }
        if(adresseRepository.findByQuartier(adresse.getQuartier())!=null){
            return ResponseEntity.ok().body(new MessageResponse("Ce quartier existe déjà"));

        }
        // if(adresseRepository.findByVille(adresse.getVille())!=null){
        //     return ResponseEntity.ok().body(new MessageResponse("Cette Ville existe déjà"));

        // }


        if(adresse.getQuartier() == ""){
            return ResponseEntity.ok().body(new MessageResponse("Le quartier est obligatoire"));

        }
        if(adresse.getVille()==""){
            return ResponseEntity.ok().body(new MessageResponse("la ville est obligatoire"));

        }
        if(adresse.getLatitude() == ""){
            return ResponseEntity.ok().body(new MessageResponse("La Latitude est obligatoire"));

        }
        if(adresse.getLongitude()==""){
            return ResponseEntity.ok().body(new MessageResponse("la Longitude est obligatoire"));

        }
        adresseService.saveAdresse(adresse);
        return ResponseEntity.ok().body(new MessageResponse("Ok"));

    }

    //METTRE A JOUR UNE AUTOECOLE
    @PatchMapping("/updateAdresse/{idadresse}")
    public ResponseEntity<?> upadateAdresse(@PathVariable("idadresse") Long id, Map<String, Object> adresse){
        return ResponseEntity.ok().body(adresseService.upadateAdresse(id, adresse));
    }

    //RETOURNER UNE AUTOECOLE PAR SON ID
    @GetMapping("/getAdresseById/{idadresse}")
    public Adresse getAdresseById(@PathVariable Long id){
        return adresseService.getAdresseById(id);
    }

    //RETOURNER TOUTES LES VEHICULE
    @GetMapping("/getAllVehicule")
    public List<Vehicule> getAllVehiule(){
        return  vehiculeService.getAllVehicule();
    }

    //AJOUTER UNE VEHICULE
    @PostMapping("/addVehicule")
    public ResponseEntity<?> addVehicule(@RequestParam("vehicule") String vehicule, @RequestParam("image") MultipartFile file){
        try {
            Vehicule vehicule1 = new JsonMapper().readValue(vehicule, Vehicule.class);

            if (file != null) {

                vehicule1.setImage(SaveImage.save("image", file, file.getOriginalFilename()));
            }
            if(vehicule1.getMarquevehicule()==""){
                return ResponseEntity.ok().body(new MessageResponse("la marque du véhicule est obligatoire"));

            }
            
            if(vehicule1.getTypevehicule()==""){
                return ResponseEntity.ok().body(new MessageResponse("Le type du véhicule est obligatoire"));

            }
            vehiculeService.AddVehicule(vehicule1);
            return ResponseEntity.ok().body(new MessageResponse("Ok"));
        }catch (Exception e){
            return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
        }

    }

    //METTRE A JOUR UNE VEHICULE
    @PatchMapping("/updatevehicule/{id}")
    public Vehicule updateEtat(@PathVariable("id") Long id, @RequestBody Map<String, Object> updates) {
        return vehiculeService.updateVehicule(id, updates);
    }

    //OBTENIR UNE VEHICULE PAR SON ID
    @GetMapping("/getvehiculeby/{id}")
    public Vehicule getById(@PathVariable Long id){
        return vehiculeService.getById(id);
    }


    //GETALL TYPECOURS
    @GetMapping("/getAllCours")
    public List<TypeCours> getallTypeCours(){
        return typeCoursService.getAllTypeCours();
    }


    //************************************PARTIE CONCERNANTS LES TYPES DE COURS DISPENSER PAR LES AUTOECOLE
    @PostMapping("/ajouterTypecours")
    public ResponseEntity<?> addTypeCours(@RequestParam("typeCours") String typeCours, @RequestParam("image") MultipartFile image){
        try {
            TypeCours typeCours1 = new JsonMapper().readValue(typeCours, TypeCours.class);
            if (image !=null){
                typeCours1.setImage(SaveImage.save("typeCours", image, image.getOriginalFilename()));
            }else {
                return ResponseEntity.ok().body(new MessageResponse("Veillez selectionner une image"));
            }
            if(typeCours1.getNomcours() == ""){
                return ResponseEntity.ok().body(new MessageResponse("Veuillez entrer le nom du cours"));
            }
            typeCoursService.saveCours(typeCours1);
            return ResponseEntity.ok().body(new MessageResponse("Ok"));
        }catch (Exception e){
            return ResponseEntity.ok().body(new MessageResponse("ERREUR LORS DE L'ENVOIE DE DONNER"));
        }
    }

    @GetMapping("/getAllTypeCours")
    public List<TypeCours> getAllTypeCours(){
        return typeCoursService.getAllTypeCours();
    }

    //METHODE UTILISER POUR RESERVER DES COURS AUPRES DES AUTOECOLES;

    @PostMapping("/reserverCours/{idAutoEcole}/{idApprenant}")
public ResponseEntity<?> reserverCours(@PathVariable("idAutoEcole") Long id, @PathVariable("idApprenant") Long idApprenant, @RequestBody Reservation reservation){
   Apprenant apprenant = apprenantRepository.getReferenceById(idApprenant);
   Autoecole autoecole = autoEcoleRepository.getReferenceById(id);
   Reservation reservation1 = reservationRepository.findByApprenantAndAutoEcole(apprenant, autoecole);
   if (apprenant != null){
       reservation.setApprenant(apprenant);
   }else {
       return ResponseEntity.ok().body(new MessageResponse("Ereeur lors du choix de l'utilisateur"));
   }

   if (autoecole != null){
       reservation.setAutoEcole(autoecole);
   }else {
       return ResponseEntity.ok().body(new MessageResponse("Erreur lors du selection de l'autoecole"));
   }


   if (reservation1 != null){
       return ResponseEntity.ok().body(new MessageResponse("Vous avez déjà reserver le cours pour cette autoécole"));
   }else{
       reservation.setEtat(EtatReservation.ATTENTE);
       sendEmail.MessageReservationEncoursDeTraitement(apprenant.getEmail(), apprenant.getPrenom(), autoecole.getNom());
       reserverCoursService.faireReservation(reservation);
       return ResponseEntity.ok().body(new MessageResponse("Ok"));
   }

}



}
