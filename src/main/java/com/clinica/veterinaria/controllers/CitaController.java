package com.clinica.veterinaria.controllers;

import com.clinica.veterinaria.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("listaCitas", citaService.listarPorFecha());
        return "citas/listado";
    }
}
