package com.tallerwiki.thymeleaf.repository;

import com.tallerwiki.thymeleaf.model.WikiPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WikiPageRepository extends JpaRepository<WikiPage, String> {

    List<WikiPage> findAllByOrderByIdAsc();

    List<WikiPage> findByCategoryIdOrderByIdAsc(String categoryId);
}
