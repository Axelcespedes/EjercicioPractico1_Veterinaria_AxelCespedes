package com.clinica.veterinaria.service;

import java.time.LocalDate;
import com.clinica.veterinaria.domain.Cita;
import java.util.List;

public interface CitaService {

    List<Cita> listarPorFecha();
    
    List<Cita> consultaAmpliada(Integer idTipo, LocalDate desde, LocalDate hasta);
}
