package com.tallerwiki.thymeleaf.repository;

import com.tallerwiki.thymeleaf.model.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiembroRepository extends JpaRepository<Miembro, Long> {

    List<Miembro> findAllByOrderByIdAsc();
}
