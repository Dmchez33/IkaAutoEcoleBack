package com.ikaautoecole.spring.projet.Configuration;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SaveImage {

    public static String localhost = "http://192.168.43.58/";

    public static String servercours = localhost + "apiikaautoecole/images/cours/";
    public static String Courslocation = "C:/xampp/htdocs/apiikaautoecole/images/cours";

    public static String servercontenu = localhost + "apiikaautoecole/images/contenu/";
    public static String Contenulocation = "C:/xampp/htdocs/apiikaautoecole/images/contenu";

    public static String servercontenuvocal = localhost + "apiikaautoecole/images/contenuvocal/";
    public static String ContenuVocallocation = "C:/xampp/htdocs/apiikaautoecole/images/contenuvocal";

    public static String serverpanneauxconduite = localhost + "apiikaautoecole/images/contenuvocal/";
    public static String Panneauxconduitelocation = "C:/xampp/htdocs/apiikaautoecole/images/contenuvocal";

    public static String serveruser = localhost + "apiikaautoecole/images/utilisateurs/";
    public static String Userlocation = "C:/xampp/htdocs/apiikaautoecole/images/utilisateurs";

    public static String servertypecours = localhost + "apiikaautoecole/images/typecours/";
    public static String TypeCourslocation= "C:/xampp/htdocs/apiikaautoecole/images/typecours";

    public static String servervehicule = localhost + "apiikaautoecole/images/vehicule/";
    public static String Vehiculelocation = "C:/xampp/htdocs/apiikaautoecole/images/vehicule";

    public static String save(String typeImage, MultipartFile file, String nomFichier) {
        String src = "";
        String server = "";
        String location = "";
        if (typeImage == "user") {
            location = Userlocation;
            server = serveruser;
        } else if (typeImage == "cours") {
            location = Courslocation;
            server = servercours;
        } else if (typeImage == "contenu") {
            location = Contenulocation;
            server = servercontenu;
        } else if (typeImage == "contenuvocal") {
            location = ContenuVocallocation;
            server = servercontenuvocal;
        }else if (typeImage == "typeCours") {
            location = TypeCourslocation;
            server = servertypecours;
        } else if (typeImage == "vehicule") {
            location = Vehiculelocation;
            server = servervehicule;
        } else if (typeImage == "panneauxConduite") {
            location = Panneauxconduitelocation;
            server = serverpanneauxconduite;
        }else {
            location = Courslocation;
            server = servercours;

        }

        /// debut de l'enregistrement
        try {
            //int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");

            Path chemin = Paths.get(location);
            if (!Files.exists(chemin)) {
                // si le fichier n'existe pas deja
                Files.createDirectories(chemin);
                Files.copy(file.getInputStream(), chemin
                        .resolve(nomFichier ));
                src = server + nomFichier;
                        //+ file.getOriginalFilename().substring(index).toLowerCase();
            } else {
                // si le fichier existe pas deja
                String newPath = location + nomFichier;
                        //+ file.getOriginalFilename().substring(index).toLowerCase();
                Path newchemin = Paths.get(newPath);
                if (!Files.exists(newchemin)) {
                    // si le fichier n'existe pas deja
                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier ));
                    src = server + nomFichier;
                            //+ file.getOriginalFilename().substring(index).toLowerCase();
                } else {
                    // si le fichier existe pas deja on le suprime et le recrèe
                    System.err.println("zone fichier existe deja");
                    Files.delete(newchemin);

                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier));
                    src = server + nomFichier;
                            //+ file.getOriginalFilename().substring(index).toLowerCase();
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
            src = null;
        }

        return src;
    }

}