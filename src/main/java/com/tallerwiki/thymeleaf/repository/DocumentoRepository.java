package com.tallerwiki.thymeleaf.repository;

import com.tallerwiki.thymeleaf.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

    List<Documento> findAllByOrderByNumeroAsc();
}
