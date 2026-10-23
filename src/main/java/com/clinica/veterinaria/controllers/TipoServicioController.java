package com.clinica.veterinaria.controllers;

import com.clinica.veterinaria.domain.TipoServicio;
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
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    public TipoServicioController(TipoServicioService tipoServicioService) {
        this.tipoServicioService = tipoServicioService;
    }

    @GetMapping
    public String listarTipos(Model model) {
        model.addAttribute("listaTipos", tipoServicioService.listarTodos());
        return "tipos/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tipoServicio", new TipoServicio());
        return "tipos/formulario";
    }

    @GetMapping("/editar/{idTipo}")
    public String mostrarFormularioEdicion(@PathVariable Integer idTipo,
                                           Model model,
                                           RedirectAttributes atributos) {
        TipoServicio tipoEncontrado = tipoServicioService.buscarPorId(idTipo);
        if (tipoEncontrado == null) {
            atributos.addFlashAttribute("mensajeError", "El tipo de servicio no existe.");
            return "redirect:/tipos";
        }
        model.addAttribute("tipoServicio", tipoEncontrado);
        return "tipos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarTipo(@ModelAttribute TipoServicio tipoServicio,
                              RedirectAttributes atributos) {
        boolean esNuevo = tipoServicio.getIdTipo() == null;
        tipoServicioService.guardarRegistro(tipoServicio);
        atributos.addFlashAttribute("mensajeExito",
                esNuevo ? "Tipo de servicio agregado correctamente."
                        : "Tipo de servicio modificado correctamente.");
        return "redirect:/tipos";
    }

    @PostMapping("/eliminar/{idTipo}")
    public String eliminarTipo(@PathVariable Integer idTipo,
                               RedirectAttributes atributos) {
        TipoServicio tipoEncontrado = tipoServicioService.buscarPorId(idTipo);
        if (tipoEncontrado != null && !tipoEncontrado.getListaServicios().isEmpty()) {
            atributos.addFlashAttribute("mensajeError",
                    "No se puede eliminar: el tipo tiene servicios asociados.");
            return "redirect:/tipos";
        }
        tipoServicioService.eliminarRegistro(idTipo);
        atributos.addFlashAttribute("mensajeExito", "Tipo de servicio eliminado correctamente.");
        return "redirect:/tipos";
    }
}
