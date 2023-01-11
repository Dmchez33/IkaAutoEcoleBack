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
}
