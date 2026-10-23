package com.clinica.veterinaria.service;

import com.clinica.veterinaria.domain.Servicio;
import java.util.List;

public interface ServicioService {

    List<Servicio> listarTodos();

    Servicio buscarPorId(Integer idServicio);

    void guardarRegistro(Servicio servicio);

    void eliminarRegistro(Integer idServicio);
}
