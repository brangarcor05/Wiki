package com.tallerwiki.thymeleaf.controller;

import com.tallerwiki.thymeleaf.model.MensajeContacto;
import com.tallerwiki.thymeleaf.service.ContactoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

    /**
     * Solo se aceptan los cinco campos del formulario (nadie puede fijar el id ni la
     * fecha desde la petición) y se quitan los espacios de los extremos, igual que
     * hace contacto.js antes de validar.
     */
    @InitBinder("mensajeContacto")
    public void configurarFormulario(WebDataBinder binder) {
        binder.setAllowedFields("nombre", "correo", "telefono", "asunto", "mensaje");
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("mensajeContacto", new MensajeContacto());
        return "contacto";
    }

    /**
     * La validación en JavaScript mejora la experiencia, pero el servidor vuelve a
     * validar: si alguien envía el formulario sin JavaScript o con datos alterados,
     * se muestra de nuevo con los errores en lugar de fallar al guardar.
     */
    @PostMapping("/contacto")
    public String guardar(@Valid @ModelAttribute("mensajeContacto") MensajeContacto mensaje,
                          BindingResult resultado,
                          RedirectAttributes redirectAttributes) {
        if (resultado.hasErrors()) {
            return "contacto";
        }
        contactoService.guardar(mensaje);
        redirectAttributes.addFlashAttribute("enviado", true);
        return "redirect:/contacto";
    }
}
