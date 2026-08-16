package com.tallerwiki.thymeleaf.service;

import com.tallerwiki.thymeleaf.model.Category;
import com.tallerwiki.thymeleaf.model.Miembro;
import com.tallerwiki.thymeleaf.model.WikiPage;
import com.tallerwiki.thymeleaf.repository.CategoryRepository;
import com.tallerwiki.thymeleaf.repository.MiembroRepository;
import com.tallerwiki.thymeleaf.repository.WikiPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WikiDataService {

    private final CategoryRepository categoryRepository;
    private final WikiPageRepository wikiPageRepository;
    private final MiembroRepository miembroRepository;

    @Autowired
    public WikiDataService(CategoryRepository categoryRepository,
                           WikiPageRepository wikiPageRepository,
                           MiembroRepository miembroRepository) {
        this.categoryRepository = categoryRepository;
        this.wikiPageRepository = wikiPageRepository;
        this.miembroRepository = miembroRepository;
    }

    public List<Category> obtenerCategorias() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

    public Optional<Category> obtenerCategoriaPorId(String id) {
        return categoryRepository.findById(id);
    }

    public List<WikiPage> obtenerPaginas() {
        return wikiPageRepository.findAllByOrderByIdAsc();
    }

    public Optional<WikiPage> obtenerPaginaPorId(String id) {
        return wikiPageRepository.findById(id);
    }

    public List<WikiPage> obtenerPaginasPorCategoria(String categoryId) {
        return wikiPageRepository.findByCategoryIdOrderByIdAsc(categoryId);
    }

    public List<Miembro> obtenerEquipo() {
        return miembroRepository.findAllByOrderByIdAsc();
    }
}
