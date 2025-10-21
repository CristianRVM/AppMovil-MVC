package com.example.AppMovil.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarControlador {
    @GetMapping("/")
    public String mostrarInicio() {
        return "home"; // Busca home.html en /templates
    }
    
    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "dashboard"; // Busca dashboard.html en /templates
    }
    
    @GetMapping("/estado-de-animo")
    public String mostrarConsejos() {
        return "estado-de-animo";   //Busca estado-de-animo.html en /templates
    }
    
    @GetMapping("/chat")
    public String mostrarChat() {
        return "chat"; // Busca chat.html en /templates
    }
    
    @GetMapping("/videollamada")
    public String mostrarVideollamada() {
        return "chat"; // Busca videollamada.html en /templates
    }
}
