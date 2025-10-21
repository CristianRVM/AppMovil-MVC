package com.example.AppMovil.Servicio;

import com.example.AppMovil.DTO.CrearEstadoDTO;
import com.example.AppMovil.DTO.ResumenEmocionDTO;
import com.example.AppMovil.Entidad.EstadoDeAnimo;
import com.example.AppMovil.Respositorio.EmocionRepositorio;
import com.example.AppMovil.Respositorio.EstadoDeAnimoRepositorio;
import com.example.AppMovil.Respositorio.UsuarioRepositorio;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoDeAnimoServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final EmocionRepositorio emocionRepositorio;
    private final EstadoDeAnimoRepositorio estadoDeAnimoRepositorio;

    public EstadoDeAnimoServicio(
            UsuarioRepositorio usuarioRepositorio,
            EmocionRepositorio emocionRepositorio,
            EstadoDeAnimoRepositorio estadoDeAnimoRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.emocionRepositorio = emocionRepositorio;
        this.estadoDeAnimoRepositorio = estadoDeAnimoRepositorio;
    }

    @Transactional
    public Long crear(CrearEstadoDTO dto) {
        var usuario = usuarioRepositorio.findById(dto.idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
        var emocion = emocionRepositorio.findByCode(dto.code)
                .orElseThrow(() -> new IllegalArgumentException("Emoción no válida"));

        var estado = new EstadoDeAnimo();
        estado.setUsuario(usuario);
        estado.setEmocion(emocion);
        estado.setTexto(dto.texto);

        estadoDeAnimoRepositorio.save(estado);
        return estado.getIdEstadoDeAnimo();
    }

    @Transactional(readOnly = true)
    public List<EstadoDeAnimo> listarPorUsuario(Integer idUsuario) {
        var usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
        return estadoDeAnimoRepositorio.findByUsuarioOrderByCreadoEnDesc(usuario);
    }

    @Transactional(readOnly = true)
    public List<EstadoDeAnimo> listarPorUsuarioYMes(Integer idUsuario, int year, int month) {
        var usuario = usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
        var desde = LocalDate.of(year, month, 1).atStartOfDay();
        var hasta = desde.plusMonths(1);
        return estadoDeAnimoRepositorio.findByUsuarioAndCreadoEnBetweenOrderByCreadoEnDesc(
                usuario, desde, hasta);
    }

    @Transactional
    public void eliminar(Long id) {
        estadoDeAnimoRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ResumenEmocionDTO> resumenPorUltimosDias(Integer idUsuario, int days) {
        var desde = java.time.LocalDateTime.now().minusDays(days);
        usuarioRepositorio.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));

        var rows = estadoDeAnimoRepositorio.resumenUltimosDiasNative(idUsuario, desde);
        return rows.stream()
                .map(r -> new ResumenEmocionDTO(
                (String) r[0], // emoji
                (String) r[1], // code
                ((Number) r[2]).longValue() // total
        ))
                .toList();
    }


    /*Wrappers*/
    @Transactional(readOnly = true)
    public List<EstadoDeAnimo> listar(Integer idUsuario) {
        return listarPorUsuario(idUsuario);
    }

    @Transactional(readOnly = true)
    public List<EstadoDeAnimo> listarMes(Integer idUsuario, int year, int month) {
        return listarPorUsuarioYMes(idUsuario, year, month);
    }

}
