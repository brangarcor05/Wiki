package com.tallerwiki.thymeleaf.controller;

import com.tallerwiki.thymeleaf.model.Category;
import com.tallerwiki.thymeleaf.service.WikiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller
@RequestMapping("/categoria")
public class CategoryController {

    private final WikiDataService wikiDataService;

    @Autowired
    public CategoryController(WikiDataService wikiDataService) {
        this.wikiDataService = wikiDataService;
    }

    @GetMapping("/{id}")
    public String verCategoria(@PathVariable String id, Model model) {
        Optional<Category> categoria = wikiDataService.obtenerCategoriaPorId(id);

        if (categoria.isEmpty()) {
            return "redirect:/wiki";
        }

        model.addAttribute("categoria", categoria.get());
        model.addAttribute("paginas", wikiDataService.obtenerPaginasPorCategoria(id));
        return "categoria-detalle";
    }
}
