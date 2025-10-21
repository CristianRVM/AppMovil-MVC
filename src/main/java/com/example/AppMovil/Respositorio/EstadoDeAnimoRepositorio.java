package com.example.AppMovil.Respositorio;

import com.example.AppMovil.DTO.ResumenEmocionDTO;
import com.example.AppMovil.Entidad.EstadoDeAnimo;
import com.example.AppMovil.Entidad.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstadoDeAnimoRepositorio extends JpaRepository<EstadoDeAnimo, Long> {

    List<EstadoDeAnimo> findByUsuarioOrderByCreadoEnDesc(Usuario usuario);

    List<EstadoDeAnimo> findByUsuarioAndCreadoEnBetweenOrderByCreadoEnDesc(
            Usuario usuario, LocalDateTime desde, LocalDateTime hasta);

    @Query(value = "SELECT em.emoji AS emoji, em.code AS code, COUNT(*) AS total "
            + "FROM EstadoDeAnimo ea "
            + "JOIN Emocion em ON em.idEmocion = ea.idEmocion "
            + "WHERE ea.idUsuario = :idUsuario "
            + "AND ea.creadoEn >= :desde "
            + "GROUP BY em.emoji, em.code "
            + "ORDER BY total DESC",
            nativeQuery = true)
    List<Object[]> resumenUltimosDiasNative(
            @Param("idUsuario") Integer idUsuario,
            @Param("desde") java.time.LocalDateTime desde
    );

}
