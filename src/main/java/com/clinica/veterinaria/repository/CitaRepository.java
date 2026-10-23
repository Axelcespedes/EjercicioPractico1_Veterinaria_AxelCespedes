package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findAllByOrderByFechaCitaAsc();
}
