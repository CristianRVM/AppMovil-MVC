package com.example.AppMovil.Controlador;

import com.example.AppMovil.DTO.CrearEstadoDTO;
import com.example.AppMovil.DTO.EstadoRespuestaDTO;
import com.example.AppMovil.DTO.ResumenEmocionDTO;
import com.example.AppMovil.Servicio.EstadoDeAnimoServicio;
import java.util.List;
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

    private final EstadoDeAnimoServicio servicio; // ✅ nombre correcto

    // ✅ inyección por constructor
    public EstadoDeAnimoControlador(EstadoDeAnimoServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public Long crear(@RequestBody CrearEstadoDTO dto) {
        return servicio.crear(dto);
    }

    @GetMapping
    public List<EstadoRespuestaDTO> listar(@RequestParam Integer usuarioId) {
        return servicio.listar(usuarioId).stream().map(e -> {
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
    public List<EstadoRespuestaDTO> listarMes(@RequestParam Integer usuarioId,
            @RequestParam int year,
            @RequestParam int month) {
        return servicio.listarMes(usuarioId, year, month).stream().map(e -> {
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
            @RequestParam Integer usuarioId,
            @RequestParam(defaultValue = "30") int days) {
        return servicio.resumenPorUltimosDias(usuarioId, days);
    }

}
