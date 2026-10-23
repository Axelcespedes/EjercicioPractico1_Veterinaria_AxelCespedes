package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findAllByOrderByFechaCitaAsc();
}
