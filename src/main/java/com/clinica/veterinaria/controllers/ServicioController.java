package com.clinica.veterinaria.controllers;

import com.clinica.veterinaria.domain.Servicio;
import com.clinica.veterinaria.service.ServicioService;
import com.clinica.veterinaria.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;

    public ServicioController(ServicioService servicioService,
                              TipoServicioService tipoServicioService) {
        this.servicioService = servicioService;
        this.tipoServicioService = tipoServicioService;
    }

    @GetMapping
    public String listarServicios(Model model) {
        model.addAttribute("listaServicios", servicioService.listarTodos());
        return "servicios/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("servicio", new Servicio());
        model.addAttribute("listaTipos", tipoServicioService.listarTodos());
        return "servicios/formulario";
    }

    @GetMapping("/editar/{idServicio}")
    public String mostrarFormularioEdicion(@PathVariable Integer idServicio,
                                           Model model,
                                           RedirectAttributes atributos) {
        Servicio servicioEncontrado = servicioService.buscarPorId(idServicio);
        if (servicioEncontrado == null) {
            atributos.addFlashAttribute("mensajeError", "El servicio no existe.");
            return "redirect:/servicios";
        }
        model.addAttribute("servicio", servicioEncontrado);
        model.addAttribute("listaTipos", tipoServicioService.listarTodos());
        return "servicios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio,
                                  RedirectAttributes atributos) {
        boolean esNuevo = servicio.getIdServicio() == null;
        servicioService.guardarRegistro(servicio);
        atributos.addFlashAttribute("mensajeExito",
                esNuevo ? "Servicio agregado correctamente."
                        : "Servicio modificado correctamente.");
        return "redirect:/servicios";
    }

    @PostMapping("/eliminar/{idServicio}")
    public String eliminarServicio(@PathVariable Integer idServicio,
                                   RedirectAttributes atributos) {
        try {
            servicioService.eliminarRegistro(idServicio);
            atributos.addFlashAttribute("mensajeExito", "Servicio eliminado correctamente.");
        } catch (Exception excepcion) {
            atributos.addFlashAttribute("mensajeError",
                    "No se puede eliminar: el servicio tiene citas asociadas.");
        }
        return "redirect:/servicios";
    }
}
