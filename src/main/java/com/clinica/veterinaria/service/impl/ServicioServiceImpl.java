package com.clinica.veterinaria.service.impl;

import com.clinica.veterinaria.domain.Servicio;
import com.clinica.veterinaria.repository.ServicioRepository;
import com.clinica.veterinaria.service.ServicioService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listarTodos() {
        return servicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio buscarPorId(Integer idServicio) {
        return servicioRepository.findById(idServicio).orElse(null);
    }

    @Override
    @Transactional
    public void guardarRegistro(Servicio servicio) {
        servicioRepository.save(servicio);
    }

    @Override
    @Transactional
    public void eliminarRegistro(Integer idServicio) {
        servicioRepository.deleteById(idServicio);
    }
}

