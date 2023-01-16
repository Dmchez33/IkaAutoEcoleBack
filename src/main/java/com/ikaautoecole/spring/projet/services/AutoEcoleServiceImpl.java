package com.ikaautoecole.spring.projet.services;

import com.ikaautoecole.spring.projet.models.Autoecole;
import com.ikaautoecole.spring.projet.repository.AutoEcoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoEcoleServiceImpl {

    @Autowired
    AutoEcoleRepository autoEcoleRepository;

    public List<Autoecole> getAutoEcole(){
        return autoEcoleRepository.findAll();
    }

}
