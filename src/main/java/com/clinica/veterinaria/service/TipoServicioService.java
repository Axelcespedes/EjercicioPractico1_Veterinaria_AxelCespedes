package com.clinica.veterinaria.service;

import com.clinica.veterinaria.domain.TipoServicio;
import java.util.List;

public interface TipoServicioService {

    List<TipoServicio> listarTodos();

    TipoServicio buscarPorId(Integer idTipo);

    void guardarRegistro(TipoServicio tipoServicio);

    void eliminarRegistro(Integer idTipo);
}
