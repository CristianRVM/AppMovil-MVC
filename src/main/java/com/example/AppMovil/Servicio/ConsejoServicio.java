package com.example.AppMovil.Servicio;

import com.example.AppMovil.Entidad.Consejo;
import com.example.AppMovil.Respositorio.ConsejoRepositorio;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsejoServicio {

    private final ConsejoRepositorio consejoRepositorio;

    public ConsejoServicio(ConsejoRepositorio consejoRepositorio) {
        this.consejoRepositorio = consejoRepositorio;
    }

    @Transactional(readOnly = true)
    public Optional<Consejo> obtenerConsejoAleatorio() {
        return consejoRepositorio.obtenerConsejoAleatorio();
    }
}
