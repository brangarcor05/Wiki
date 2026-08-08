package com.tallerwiki.thymeleaf.controller;

import com.tallerwiki.thymeleaf.service.WikiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final WikiDataService wikiDataService;

    @Autowired
    public HomeController(WikiDataService wikiDataService) {
        this.wikiDataService = wikiDataService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titulo", "Sistema de Gestión de Procesos Multiempresa");
        model.addAttribute("descripcion",
                "Wiki de presentación del proyecto: qué es, qué hace y quién lo desarrolla.");
        model.addAttribute("categorias", wikiDataService.obtenerCategorias());
        model.addAttribute("totalPaginas", wikiDataService.obtenerPaginas().size());
        return "index";
    }
}
