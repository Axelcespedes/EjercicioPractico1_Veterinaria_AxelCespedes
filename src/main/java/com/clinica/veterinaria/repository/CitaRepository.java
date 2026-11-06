package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findAllByOrderByFechaCitaAsc();
    @Query("""
        SELECT c FROM Cita c
        JOIN FETCH c.servicio s
        JOIN FETCH s.tipoServicio t
        WHERE (:idTipo IS NULL OR t.idTipo = :idTipo)
          AND (:desde IS NULL OR c.fechaCita >= :desde)
          AND (:hasta IS NULL OR c.fechaCita <= :hasta)
        ORDER BY c.fechaCita ASC, c.nombreMascota ASC
        """)
List<Cita> consultaAmpliada(@Param("idTipo") Integer idTipo,
                            @Param("desde") LocalDate desde,
                            @Param("hasta") LocalDate hasta);
}
