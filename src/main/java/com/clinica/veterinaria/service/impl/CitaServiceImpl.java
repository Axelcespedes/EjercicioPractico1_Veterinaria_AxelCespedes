package com.clinica.veterinaria.service.impl;

import com.clinica.veterinaria.domain.Cita;
import com.clinica.veterinaria.repository.CitaRepository;
import com.clinica.veterinaria.service.CitaService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> listarPorFecha() {
        return citaRepository.findAllByOrderByFechaCitaAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> consultaAmpliada(Integer idTipo, LocalDate desde, LocalDate hasta) {
        return citaRepository.consultaAmpliada(idTipo, desde, hasta);
    }
}
