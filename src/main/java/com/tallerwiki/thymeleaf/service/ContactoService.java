package com.tallerwiki.thymeleaf.service;

import com.tallerwiki.thymeleaf.model.MensajeContacto;
import com.tallerwiki.thymeleaf.repository.MensajeContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {

    private final MensajeContactoRepository mensajeContactoRepository;

    @Autowired
    public ContactoService(MensajeContactoRepository mensajeContactoRepository) {
        this.mensajeContactoRepository = mensajeContactoRepository;
    }

    public MensajeContacto guardar(MensajeContacto mensaje) {
        return mensajeContactoRepository.save(mensaje);
    }
}