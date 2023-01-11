package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.Configuration.SendEmail;
import com.ikaautoecole.spring.projet.models.AdminAutoEcole;
import com.ikaautoecole.spring.projet.models.SuperAdmin;
import com.ikaautoecole.spring.projet.repository.AdminautoecoleRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@Service
public class AdminAutoEcoleDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AdminautoecoleRepository adminautoecoleRepository;

    @Autowired
    SendEmail sendEmail;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminAutoEcole user = adminautoecoleRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec le nom: " + username));

        return UserDetailsImpl.build(user);
    }

    @Transactional
    public AdminAutoEcole updatePartial(Long id, Map<String, Object> updates) {
        AdminAutoEcole yourObject = adminautoecoleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("YourObject not found"));

        updates.forEach((k, v) -> {
            BeanWrapper beanWrapper = new BeanWrapperImpl(yourObject);
            beanWrapper.setPropertyValue(k, v);
        });
        sendEmail.MessageDeRetour(yourObject.getEmail(), yourObject.getUsername(), yourObject.getPassword());

        return adminautoecoleRepository.save(yourObject);
    }
}
