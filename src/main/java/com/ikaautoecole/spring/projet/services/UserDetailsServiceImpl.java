package com.ikaautoecole.spring.projet.services;


import com.ikaautoecole.spring.projet.models.*;
import com.ikaautoecole.spring.projet.repository.AdminautoecoleRepository;
import com.ikaautoecole.spring.projet.repository.ApprenantRepository;
import com.ikaautoecole.spring.projet.repository.SuperAdminRepository;
import com.ikaautoecole.spring.projet.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec le nom: " + username));

        return UserDetailsImpl.build(utilisateur);
    }

}