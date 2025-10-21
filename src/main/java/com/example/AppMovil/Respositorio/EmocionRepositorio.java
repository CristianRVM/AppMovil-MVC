package com.example.AppMovil.Respositorio;

import com.example.AppMovil.Entidad.Emocion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmocionRepositorio extends JpaRepository<Emocion, Integer> {
  Optional<Emocion> findByCode(String code);
}
