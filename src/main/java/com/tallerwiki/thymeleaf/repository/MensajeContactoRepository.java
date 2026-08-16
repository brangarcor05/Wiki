package com.tallerwiki.thymeleaf.repository;

import com.tallerwiki.thymeleaf.model.MensajeContacto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeContactoRepository extends JpaRepository<MensajeContacto, Long> {

    List<MensajeContacto> findAllByOrderByIdDesc();
}