package com.tallerwiki.thymeleaf.controller;

import com.tallerwiki.thymeleaf.model.WikiPage;
import com.tallerwiki.thymeleaf.service.WikiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/wiki")
public class WikiController {

    private final WikiDataService wikiDataService;

    @Autowired
    public WikiController(WikiDataService wikiDataService) {
        this.wikiDataService = wikiDataService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("paginas", wikiDataService.obtenerPaginas());
        return "wiki-lista";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        Optional<WikiPage> pagina = wikiDataService.obtenerPaginaPorId(id);

        if (pagina.isEmpty()) {
            return "redirect:/wiki";
        }

        model.addAttribute("pagina", pagina.get());
        return "wiki-detalle";
    }
}
