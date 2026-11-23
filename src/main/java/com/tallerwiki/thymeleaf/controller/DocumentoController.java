package com.tallerwiki.thymeleaf.controller;

import com.tallerwiki.thymeleaf.model.Documento;
import com.tallerwiki.thymeleaf.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class DocumentoController {

    private final DocumentoService documentoService;

    @Autowired
    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping("/documentos")
    public String documentos(Model model) {
        List<Documento> documentos = documentoService.obtenerDocumentos();
        long disponibles = documentos.stream().filter(Documento::isDisponible).count();

        model.addAttribute("documentos", documentos);
        model.addAttribute("disponibles", disponibles);
        return "documentos";
    }

    /**
     * Vista HTML de un documento. El patrón {numero:\d+} solo acepta números, así que
     * las descargas (/documentos/01_Resumen_Ejecutivo.docx) siguen llegando a los
     * archivos estáticos y no a este método.
     */
    @GetMapping("/documentos/{numero:\\d+}")
    public String verDocumento(@PathVariable Integer numero, Model model) {
        Optional<Documento> documento = documentoService.obtenerDocumento(numero);
        if (documento.isEmpty()) {
            return "redirect:/documentos";
        }

        Optional<String> html = documentoService.obtenerHtml(documento.get());
        if (html.isEmpty()) {
            return "redirect:/documentos";
        }

        model.addAttribute("documento", documento.get());
        model.addAttribute("contenidoHtml", html.get());
        return "documento-detalle";
    }
}
