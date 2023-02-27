package com.ikaautoecole.spring.projet.Configuration;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmail {

    @Autowired
    JavaMailSender mailSender;

    public void sendWelcomeEmail(String to, String name) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("idrdem83@gmail.com");
            helper.setTo(to);
            helper.setSubject("Bienvenue sur notre plateforme IkaAutoEcole!");
            helper.setText("Bonjour " + name + ",\n\nBienvenue sur notre plateforme IkaAutoEcole! Nous sommes heureux de vous compter parmi nos utilisateurs.\nVous allez recévoir un E-mail de validation de votre compte dans un delai de 2 Jours.\n Veillez rester en écoute Mr/Mme"+name+"\n\nCordialement,\nL'équipe IkaAutoEcole");
            System.out.println("Mail Send...");
            mailSender.send(message);
        } catch (MessagingException ex) {
            // gérer l'exception
        }
    }

    public void MessageDeRetour(String to, String name, String password) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("idrdem83@gmail.com");
            helper.setTo(to);
            helper.setSubject("Bienvenue sur notre plateforme IkaAutoEcole!");
            helper.setText("Bonjour " + name + ",\n\nNous sommes heureux de vous anoncé que votre compte a été validé avec success!\nVos identifiants sont.\n Nom d'utilisateur:"+name+"\n\nMot de passe:"+password+"\nVeuillez modifier votre mot de passe après votre connexion\n\n");
            System.out.println("Mail Send...");
            mailSender.send(message);
        } catch (MessagingException ex) {
            // gérer l'exception
        }
    }

    public void MessageReservationEncoursDeTraitement(String to, String name, String nomAutoEcole) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("idrdem83@gmail.com");
            helper.setTo(to);
            helper.setSubject("Equipe de l'auto-école!"+nomAutoEcole);
            helper.setText("Bonjour " + name + ",\nVotre demande de reservation est encours de traitement \n Vous serez contacté par notre équipe dans un delai de 2h \n Veillez rester en écoute Mr/Mme"+name+"\n\nCordialement,\nL'équipe "+nomAutoEcole);
            System.out.println("Mail Send...");
            mailSender.send(message);
        } catch (MessagingException ex) {
            // gérer l'exception
        }
     }


     public void MessageDemandeCreationComptepouruneAutoEcoel(String email, String nom, String prenom, String username, String telephone, String ville, String quartier, String rue, String porte, String nomAutoEcole) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email);
            helper.setTo("idrdem83@gmail.com");
            helper.setSubject("Demande de creation de compte et enregistrement de son auto-école");
            helper.setText(
                "Cher responsable de la plateforme IkaAutoEcole \n\n"+
                "Je souhaiterais créer un compte de propriétaire d'auto-école sur votre plateforme et enregistrer mon auto-école sur celle-ci.\n"+
                "Voici les informations à fournir pour la création de mon compte et l'enregistrement de mon auto-école sur votre plateforme\n"+
                "Nom : " + nom + "\n" +
                "Prenom : " + prenom+ "\n" +
                "Nom d'utilisateur : "+username+ "\n"+
                "Telephone : " + telephone + "\n"+
                "email : " + email + "\n\n" +
                "L'adresse de mon auto-école :\n"+
                "Le nom de mon auto-école : " + nomAutoEcole +
                "\nVille ou se trouve auto-école " + ville + "\n" +
                "Quartier ou se trouve auto-école " + ville + "\n" +
                "Numéro de porte : " + porte +
                "\nNuméro de la rue : " + rue
            );
            System.out.println("Mail Send...");
            mailSender.send(message);
        } catch (MessagingException ex) {
            // gérer l'exception
        }
     }

     public void MessageEnvoyeDeDemande(String to) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("idrdem83@gmail.com");
            helper.setTo(to);
            helper.setSubject("Equipe IkaAutoEcole!");
            helper.setText("Cher utilisateur,\n"+
            "Nous vous remercions d'avoir soumis votre demande de création de compte de propriétaire d'auto-école et d'enregistrement de votre auto-école sur notre plateforme.\n"+ 
            "Nous avons bien reçu votre demande et nous allons la traiter dans les meilleurs délais. Une fois que nous aurons examiné les informations que vous nous avez fournies, nous vous enverrons un e-mail de confirmation pour vous informer que votre compte a été créé et que votre auto-école a été ajoutée à notre plateforme.\n" +
            "Si nous avons besoin de plus d'informations pour traiter votre demande, nous vous contacterons par e-mail ou par téléphone.\n" +
            "Nous sommes heureux de vous compter parmi nos clients potentiels et nous sommes impatients de travailler avec vous.\n\n"+ 
            "Cordialement,\n"+
            "L'équipe de la plateforme d'auto-école. ");
            System.out.println("Mail Send...");
            mailSender.send(message);
        } catch (MessagingException ex) {
            // gérer l'exception
        }
     }

}
