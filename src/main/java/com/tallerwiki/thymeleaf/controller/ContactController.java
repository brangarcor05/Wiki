package com.tallerwiki.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ContactController {

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}
