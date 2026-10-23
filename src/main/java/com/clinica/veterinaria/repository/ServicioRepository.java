package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
}
