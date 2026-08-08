package com.tallerwiki.thymeleaf.controller;

import com.tallerwiki.thymeleaf.service.WikiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EquipoController {

    private final WikiDataService wikiDataService;

    @Autowired
    public EquipoController(WikiDataService wikiDataService) {
        this.wikiDataService = wikiDataService;
    }

    @GetMapping("/equipo")
    public String equipo(Model model) {
        model.addAttribute("equipo", wikiDataService.obtenerEquipo());
        return "equipo";
    }
}
