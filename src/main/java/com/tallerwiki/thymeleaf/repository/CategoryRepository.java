package com.tallerwiki.thymeleaf.repository;

import com.tallerwiki.thymeleaf.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findAllByOrderByIdAsc();
}
