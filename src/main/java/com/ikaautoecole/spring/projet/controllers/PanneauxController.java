package com.ikaautoecole.spring.projet.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ikaautoecole.spring.projet.Configuration.SaveImage;
import com.ikaautoecole.spring.projet.DTO.response.MessageResponse;
import com.ikaautoecole.spring.projet.models.PanneauxConduite;
import com.ikaautoecole.spring.projet.models.TypePanneaux;
import com.ikaautoecole.spring.projet.repository.PanneauxConduiteRepository;
import com.ikaautoecole.spring.projet.repository.TypePanneauxRepository;
import com.ikaautoecole.spring.projet.services.PanneauxServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/panneauxCours")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PanneauxController {

    @Autowired
    PanneauxServiceImpl panneauxService;

    @Autowired
    TypePanneauxRepository typePanneauxRepository;

    @Autowired
    PanneauxConduiteRepository panneauxConduiteRepository;

    @GetMapping("/getAll")
    public List<PanneauxConduite> getAll(){

        return panneauxService.getAllPanneaux();
    }

    @PostMapping("/post")
    public ResponseEntity<?> addPanneaux(@RequestParam("panneaux") String panneauxConduite, @RequestParam("image") MultipartFile image, @RequestParam("vocal") MultipartFile vocal){
        try{
            PanneauxConduite panneauxConduite1 = new JsonMapper().readValue(panneauxConduite, PanneauxConduite.class);
            if (image != null){
                panneauxConduite1.setImage(SaveImage.save("panneauxConduite", image, image.getOriginalFilename() ));
            }else {
                return ResponseEntity.ok().body(new MessageResponse("Veuillez selectionner une image"));
            }
            if (vocal != null){
                panneauxConduite1.setVocal(SaveImage.save("panneauxConduite",vocal,vocal.getOriginalFilename()));
            }else {
                return ResponseEntity.ok().body(new MessageResponse("Veuillez selectionner un audio"));
            }
            panneauxService.CreatePanneaux(panneauxConduite1);
            return ResponseEntity.ok().body(new MessageResponse("Ok"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("ERREUR LORS DE L'ENVOIE DE LA SOLUTION"));
        }

    }

    @GetMapping("/getById/{id}")
    public PanneauxConduite getById(@PathVariable("id") Long id){
        return panneauxService.getPanneauxByID(id);
    }

    @GetMapping("/getByNom/{nom}")
    public PanneauxConduite getByNom(@PathVariable("nom") String nom){
        return panneauxService.getByNomPanneau(nom);
    }

    @GetMapping("/getBytitreType/{titre}") 
    public List<PanneauxConduite> getByTitrePanneau(@PathVariable("titre") String titre)
    {
        TypePanneaux typePanneaux = typePanneauxRepository.findByType(titre);
        if (typePanneaux != null)
        {
            return panneauxConduiteRepository.findByTypePanneaux(typePanneaux);
        }
        else {
            return null;
        }
    }

    //METHODE POUR LES TYPE DE PANNEAUX
    @PostMapping("/addType")
    public ResponseEntity<?> postPanneauxType(@RequestParam("typePanneaux") String typePanneaux, @RequestParam("image") MultipartFile image){
        try{
            TypePanneaux typePanneaux1 = new JsonMapper().readValue(typePanneaux, TypePanneaux.class);
            if (image != null){
                typePanneaux1.setImage(SaveImage.save("panneauxConduite", image, image.getOriginalFilename()));
            }else{
                return ResponseEntity.ok().body(new MessageResponse("Veuillez selectionner une image"));
            }
            panneauxService.addPanneaux(typePanneaux1);
            return ResponseEntity.ok().body(new MessageResponse("Ok"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("ERREUR LORS DE L'ENVOIE DE LA SOLUTION");
        }

    }

    @GetMapping("/getAllType")
    public List<TypePanneaux> getAllTypePanneaux(){
        return panneauxService.getTypePanneaux();
    }


}
