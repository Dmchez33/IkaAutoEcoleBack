package com.ikaautoecole.spring.projet.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ikaautoecole.spring.projet.Configuration.Audio;
import com.ikaautoecole.spring.projet.Configuration.SaveImage;
import com.ikaautoecole.spring.projet.DTO.request.AdminAutoEcoleRequest;
import com.ikaautoecole.spring.projet.DTO.response.MessageResponse;
import com.ikaautoecole.spring.projet.models.ContenuCours;
import com.ikaautoecole.spring.projet.models.Cours;
import com.ikaautoecole.spring.projet.models.TypeCours;
import com.ikaautoecole.spring.projet.repository.CoursContenuRepository;
import com.ikaautoecole.spring.projet.repository.CoursRepository;
import com.ikaautoecole.spring.projet.repository.TypeCoursRepository;
import com.ikaautoecole.spring.projet.services.CoursServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cours")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CoursController {
    @Autowired
    CoursServiceImpl coursService;

    @Autowired
    CoursRepository coursRepository;


    @Autowired
    CoursContenuRepository contenuRepository;
    @GetMapping("/getCours")
    public List<Cours> get(){
        return coursService.getCours();
    }

    @GetMapping("/getCours/{id}")
    public Cours getById(@PathVariable("id") Long id){
        return coursService.getCoursById(id);
    }

    @PostMapping("/postCours")
    public ResponseEntity<?> post(@RequestParam(value = "cours") String cours,@RequestParam(value = "file", required = false) MultipartFile file){
        try {
            Cours cours1 = new JsonMapper().readValue(cours, Cours.class);
            if (file != null) {
                System.out.println("Enregistrement du fichier");
                cours1.setImage(SaveImage.save("cours", file, file.getOriginalFilename()));
            }
            else {
                return ResponseEntity.ok().body(new MessageResponse("Veuillez selectionner une image"));
            }

            if (cours1.getLibelle() == "")
            {
                return ResponseEntity.ok().body(new MessageResponse("Veuillez donner le nom du cours"));
            }
            coursService.saveCours(cours1);

            return ResponseEntity.ok().body(new MessageResponse("Ok"));
        }catch (Exception e){
            return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
        }

    }

    //GET ALL CONTENU
    @GetMapping("/getContenu")
    public List<ContenuCours> getContenue(){
        return coursService.getContenuCours();
    }
    //GET CONTENU BY ID
    @GetMapping("/getContenu/{id}")
    public ContenuCours getContenueById(@PathVariable("id") Long id){
        return coursService.getContenuCoursById(id);
    }

    //POST CONTENU
    @PostMapping("/postContenu")
    public ResponseEntity<?> postContenu(@RequestParam(value = "contenu") String Contenu,@RequestParam(value = "file", required = false) MultipartFile file,@RequestParam(value = "description", required = false) MultipartFile description,@RequestParam(value = "audio", required = false) MultipartFile audio){
        try {
            ContenuCours contenuCours = new JsonMapper().readValue(Contenu, ContenuCours.class);
            if (file != null) {
                System.out.println("Enregistrement de l'image");
                //String nomImage[] = file.getOriginalFilename().split(".");
                contenuCours.setImage(SaveImage.save("cours", file, file.getOriginalFilename()));

            }else{
                return ResponseEntity.ok().body( new MessageResponse("VEILLEZ SELECTIONNER UNE IMAGE"));
            }
            if (description != null) {
                System.out.println("Enregistrement de l'image");
                //String nomImage[] = file.getOriginalFilename().split(".");
                contenuCours.setDescription(SaveImage.save("contenu", description, file.getOriginalFilename()));

            }else{
                return ResponseEntity.ok().body( new MessageResponse("VEILLEZ SELECTIONNER UNE IMAGE"));
            }
            if (audio != null) {
                System.out.println("Enregistrement de l'audio");
                //String nomvocal[] = audio.getOriginalFilename().split(".");
                String uploadDir = Audio.SOURCE_DIR+"cours";//System.getProperty("user.dir") + "/assets/aud";
                //String uploadDir = System.getProperty("java.io.tmpdir") + "assets/aud"; //Pour heroku
                File convFile = new File(audio.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(convFile);
                fos.write(audio.getBytes());
                fos.close();
                Audio.saveAudio(uploadDir, convFile);
                contenuCours.setVocal(audio.getOriginalFilename());
            }else{
                return ResponseEntity.ok().body( new MessageResponse("VEILLEZ SELECTIONNER UN AUDIO"));
            }
            coursService.saveContenu(contenuCours);
            return ResponseEntity.ok().body( new MessageResponse("Ok")) ;
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }

    }

    //UDATE CONTENU COURS

    @GetMapping("/getContenuByCour/{id}")
    public List<ContenuCours> getcontenuCourByCours(@PathVariable("id") Long id){
        Cours cours = coursRepository.getReferenceById(id);
        return contenuRepository.findContenuCoursByCours(cours);
    }




}
