package com.ikaautoecole.spring.projet;

import com.ikaautoecole.spring.projet.models.SuperAdmin;
import com.ikaautoecole.spring.projet.models.Utilisateur;
import com.ikaautoecole.spring.projet.models.ERole;
import com.ikaautoecole.spring.projet.models.Role;
import com.ikaautoecole.spring.projet.repository.SuperAdminRepository;
import com.ikaautoecole.spring.projet.repository.UtilisateurRepository;
import com.ikaautoecole.spring.projet.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@SpringBootApplication
class SecuComApplication implements CommandLineRunner {

	//**************************** DECLARATION DES INSTANCE *****************
	@Autowired
	PasswordEncoder encoder;

	final private RoleRepository roleRepository;

	final private SuperAdminRepository superAdminRepository;

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
			SuperAdmin collaborateur = new SuperAdmin("admin","admin@gmail.com",encoder.encode( "12345678"));
			collaborateur.setRoles(roles);
			superAdminRepository.save(collaborateur);

		}
	}
}

