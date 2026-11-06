package com.clinica.veterinaria.controllers;

import com.clinica.veterinaria.domain.Cita;
import com.clinica.veterinaria.service.CitaService;
import com.clinica.veterinaria.service.TipoServicioService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;
    private final TipoServicioService tipoServicioService;

    public CitaController(CitaService citaService,
                          TipoServicioService tipoServicioService) {
        this.citaService = citaService;
        this.tipoServicioService = tipoServicioService;
    }

    @GetMapping
    public String listarCitas(Model model) {
        model.addAttribute("listaCitas", citaService.listarPorFecha());
        return "citas/listado";
    }

    @GetMapping("/consulta")
    public String consultaAmpliada(
            @RequestParam(required = false) Integer idTipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            Model model) {

        List<Cita> resultados = citaService.consultaAmpliada(idTipo, desde, hasta);
        BigDecimal totalEstimado = resultados.stream()
                .map(cita -> cita.getServicio().getPrecioServicio())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("listaCitas", resultados);
        model.addAttribute("totalEstimado", totalEstimado);
        model.addAttribute("listaTipos", tipoServicioService.listarTodos());
        model.addAttribute("idTipo", idTipo);
        model.addAttribute("desde", desde);
        model.addAttribute("hasta", hasta);
        return "citas/consulta";
    }
}