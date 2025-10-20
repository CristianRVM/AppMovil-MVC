package com.example.AppMovil.Respositorio;

import com.example.AppMovil.Entidad.EstadoDeAnimo;
import com.example.AppMovil.Entidad.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstadoDeAnimoRepositorio extends JpaRepository<EstadoDeAnimo, Long> {
  List<EstadoDeAnimo> findByUsuarioOrderByCreadoEnDesc(Usuario usuario);
  List<EstadoDeAnimo> findByUsuarioAndCreadoEnBetweenOrderByCreadoEnDesc(
      Usuario usuario, LocalDateTime desde, LocalDateTime hasta);
}
