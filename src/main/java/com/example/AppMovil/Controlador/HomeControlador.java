package com.example.AppMovil.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {

    @GetMapping("/")
    public String mostrarInicio() {
        return "home"; // Busca home.html en /templates
    }
}
