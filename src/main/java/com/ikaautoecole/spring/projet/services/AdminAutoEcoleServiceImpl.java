package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.Configuration.SendEmail;
import com.ikaautoecole.spring.projet.models.AdminAutoEcole;
import com.ikaautoecole.spring.projet.repository.AdminautoecoleRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@Service
public class AdminAutoEcoleServiceImpl {
    @Autowired
    AdminautoecoleRepository adminautoecoleRepository;

    @Autowired
    SendEmail sendEmail;



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
