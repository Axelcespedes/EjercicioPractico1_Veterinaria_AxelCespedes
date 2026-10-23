package com.clinica.veterinaria.service.impl;

import com.clinica.veterinaria.domain.Cita;
import com.clinica.veterinaria.repository.CitaRepository;
import com.clinica.veterinaria.service.CitaService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
