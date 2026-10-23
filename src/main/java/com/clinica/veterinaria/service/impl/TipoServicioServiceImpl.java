package com.clinica.veterinaria.service.impl;

import com.clinica.veterinaria.domain.TipoServicio;
import com.clinica.veterinaria.repository.TipoServicioRepository;
import com.clinica.veterinaria.service.TipoServicioService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    public TipoServicioServiceImpl(TipoServicioRepository tipoServicioRepository) {
        this.tipoServicioRepository = tipoServicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> listarTodos() {
        return tipoServicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoServicio buscarPorId(Integer idTipo) {
        return tipoServicioRepository.findById(idTipo).orElse(null);
    }

    @Override
    @Transactional
    public void guardarRegistro(TipoServicio tipoServicio) {
        tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public void eliminarRegistro(Integer idTipo) {
        tipoServicioRepository.deleteById(idTipo);
    }
}