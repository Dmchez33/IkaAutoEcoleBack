package com.ikaautoecole.spring.projet;

import com.ikaautoecole.spring.projet.Configuration.JacksonConfig;
import com.ikaautoecole.spring.projet.models.*;
import com.ikaautoecole.spring.projet.repository.AdminautoecoleRepository;
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

	//**************************** METHODE PRINCIPALE DE L'APPLICATION ***********
	public static void main(String[] args) {
		SpringApplication.run(SecuComApplication.class, args);

	}

	//***************************** METHODE PERMETTANT DE CREER UN ADMIN PAR DEFAUT **********
	@Override
	public void run(String... args) throws Exception {
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
		if (adminautoecoleRepository.findAll().size() == 0){
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(ERole.ROLE_ADMIN_AUTOECOLE);
			roles.add(role);
			AdminAutoEcole collaborateur1 = new AdminAutoEcole("idriss","idriss@gmail.com", encoder.encode("12345678"),"12-12-2020","bamako","kanaga");
			collaborateur1.setRoles(roles);
			adminautoecoleRepository.save(collaborateur1);
		}
	}
}

