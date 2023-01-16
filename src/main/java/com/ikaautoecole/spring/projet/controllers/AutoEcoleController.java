package com.ikaautoecole.spring.projet.controllers;

import com.ikaautoecole.spring.projet.models.Autoecole;
import com.ikaautoecole.spring.projet.services.AutoEcoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/AutoEcole")
public class AutoEcoleController {

    @Autowired
    AutoEcoleServiceImpl autoEcoleService;

    @GetMapping("/getAll")
    public List<Autoecole> get(){
        return autoEcoleService.getAutoEcole();
    }
}
