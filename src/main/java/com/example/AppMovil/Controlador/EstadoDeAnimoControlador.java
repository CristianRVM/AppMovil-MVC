package com.example.AppMovil.Controlador;

import com.example.AppMovil.DTO.CrearEstadoDTO;
import com.example.AppMovil.DTO.EstadoRespuestaDTO;
import com.example.AppMovil.DTO.ResumenEmocionDTO;
import com.example.AppMovil.Entidad.Usuario;
import com.example.AppMovil.Respositorio.UsuarioRepositorio;
import com.example.AppMovil.Servicio.EstadoDeAnimoServicio;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estados")
public class EstadoDeAnimoControlador {

    private final EstadoDeAnimoServicio servicio;
    private final UsuarioRepositorio usuarioRepositorio;

    public EstadoDeAnimoControlador(
            EstadoDeAnimoServicio servicio,
            UsuarioRepositorio usuarioRepositorio) {
        this.servicio = servicio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @PostMapping
    public Long crear(
            @AuthenticationPrincipal UserDetails principal,
            @RequestBody CrearEstadoDTO dto) {
        var usuario = obtenerUsuarioAutenticado(principal);
        return servicio.crear(usuario.getId(), dto);
    }

    @GetMapping
    public List<EstadoRespuestaDTO> listar(@AuthenticationPrincipal UserDetails principal) {
        var usuario = obtenerUsuarioAutenticado(principal);
        return servicio.listar(usuario.getId()).stream().map(e -> {
            var r = new EstadoRespuestaDTO();
            r.id = e.getIdEstadoDeAnimo();
            r.emocion = e.getEmocion().getNombre();
            r.emoji = e.getEmocion().getEmoji();
            r.texto = e.getTexto();
            r.ts = e.getCreadoEn();
            return r;
        }).toList();
    }

    @GetMapping("/mes")
    public List<EstadoRespuestaDTO> listarMes(
            @AuthenticationPrincipal UserDetails principal,
            @RequestParam int year,
            @RequestParam int month) {
        var usuario = obtenerUsuarioAutenticado(principal);
        return servicio.listarMes(usuario.getId(), year, month).stream().map(e -> {
            var r = new EstadoRespuestaDTO();
            r.id = e.getIdEstadoDeAnimo();
            r.emocion = e.getEmocion().getNombre();
            r.emoji = e.getEmocion().getEmoji();
            r.texto = e.getTexto();
            r.ts = e.getCreadoEn();
            return r;
        }).toList();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }

    @GetMapping("/resumen")
    public List<ResumenEmocionDTO> resumen(
            @AuthenticationPrincipal UserDetails principal,
            @RequestParam(defaultValue = "30") int days) {
        var usuario = obtenerUsuarioAutenticado(principal);
        return servicio.resumenPorUltimosDias(usuario.getId(), days);
    }

    private Usuario obtenerUsuarioAutenticado(UserDetails principal) {
        if (principal == null) {
            throw new IllegalStateException("Usuario no autenticado");
        }
        var usuario = usuarioRepositorio.findByCorreo(principal.getUsername());
        if (usuario == null) {
            throw new IllegalStateException("Usuario autenticado no existe");
        }
        return usuario;
    }
}
