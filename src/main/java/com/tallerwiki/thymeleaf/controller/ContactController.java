package com.tallerwiki.thymeleaf.controller;

import com.tallerwiki.thymeleaf.model.MensajeContacto;
import com.tallerwiki.thymeleaf.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ContactController {

    private final ContactoService contactoService;

    @Autowired
    public ContactController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("mensajeContacto", new MensajeContacto());
        return "contacto";
    }

    @PostMapping("/contacto")
    public String guardar(@ModelAttribute("mensajeContacto") MensajeContacto mensaje,
                          RedirectAttributes redirectAttributes) {
        contactoService.guardar(mensaje);
        redirectAttributes.addFlashAttribute("enviado", true);
        return "redirect:/contacto";
    }
}
