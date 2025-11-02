package com.example.AppMovil.Respositorio;

import com.example.AppMovil.Entidad.Consejo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsejoRepositorio extends JpaRepository<Consejo, Long> {

    @Query(value = "SELECT * FROM Consejo ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Consejo> obtenerConsejoAleatorio();
}
