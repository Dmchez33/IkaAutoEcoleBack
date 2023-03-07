package com.ikaautoecole.spring.projet;

import com.ikaautoecole.spring.projet.Configuration.JacksonConfig;
import com.ikaautoecole.spring.projet.models.*;
import com.ikaautoecole.spring.projet.repository.AdminautoecoleRepository;
import com.ikaautoecole.spring.projet.repository.AdresseRepository;
import com.ikaautoecole.spring.projet.repository.SuperAdminRepository;
import com.ikaautoecole.spring.projet.repository.RoleRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@SpringBootApplication
@Import(JacksonConfig.class)
class SecuComApplication implements CommandLineRunner {

	//**************************** DECLARATION DES INSTANCE *****************
	@Autowired
	PasswordEncoder encoder;

	final private RoleRepository roleRepository;

	final private SuperAdminRepository superAdminRepository;

	final private AdminautoecoleRepository adminautoecoleRepository;

	final private AdresseRepository adresseRepository;

	//**************************** METHODE PRINCIPALE DE L'APPLICATION ***********
	public static void main(String[] args) {
		SpringApplication.run(SecuComApplication.class, args);

	}

	//***************************** METHODE PERMETTANT DE CREER UN ADMIN PAR DEFAUT **********
	@Override
	public void run(String... args) throws Exception {
		//CREATION DE QUELQUES ADRESSE LORS DE LANCE DE L'APPLICATION
		if (adresseRepository.findAll().size() == 0){
			adresseRepository.save(new Adresse("Bamako","Badalabougou","-7.995867603743257","12.61817499169878"));
			adresseRepository.save(new Adresse("Bamako","Missabougou","-8.087149827340227","12.1559808306058"));
			adresseRepository.save(new Adresse("Bamako","Hamdalaye ACI","-8.027916308840917","12.634621378686209"));
			adresseRepository.save(new Adresse("Bamako","Bagadadji","-7.98730470210319","12.649990266630365"));
			adresseRepository.save(new Adresse("Bamako","Djikoroni","-8.0335159084841","12.61538696748839"));
			adresseRepository.save(new Adresse("Bamako","N'tabacoro","-7.970790622061678","12.573081466229535"));

		}
		//VERIFICATION DE L'EXISTANCE DU ROLE ADMIN AVANT SA CREATION
		if (roleRepository.findAll().size() == 0){

			roleRepository.save(new Role(ERole.ROLE_SUPER_ADMIN));
			roleRepository.save(new Role(ERole.ROLE_ADMIN_AUTOECOLE));
			roleRepository.save(new Role(ERole.ROLE_APPRENANT));

		}
		if (superAdminRepository.findAll().size() == 0){
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
			roles.add(role);
			SuperAdmin collaborateur = new SuperAdmin("idrissa","admin@gmail.com",encoder.encode( "12345678"),"83252448");
			collaborateur.setRoles(roles);
			superAdminRepository.save(collaborateur);
		}

	}
}

