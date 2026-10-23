package com.clinica.veterinaria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InicioController {

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        return "inicio";
    }

    @PostMapping("/consultas/enviar")
    public String enviarConsulta(
            @RequestParam String nombreRemitente,
            @RequestParam String correoRemitente,
            @RequestParam String nombreMascota,
            @RequestParam String mensajeConsulta,
            RedirectAttributes atributos) {

        atributos.addFlashAttribute("mensajeExito",
                "¡Gracias, " + nombreRemitente + "! Recibimos tu consulta sobre " + nombreMascota + ".");
        return "redirect:/#consultas";
    }
}
