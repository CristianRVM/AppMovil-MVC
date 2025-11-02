package com.example.AppMovil.Controlador;

import com.example.AppMovil.Servicio.ConsejoServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarControlador {

    private final ConsejoServicio consejoServicio;

    public NavbarControlador(ConsejoServicio consejoServicio) {
        this.consejoServicio = consejoServicio;
    }

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        String consejo = consejoServicio.obtenerConsejoAleatorio()
                .map(c -> c.getContenido())
                .orElse("No hay consejos disponibles en este momento.");
        model.addAttribute("consejoAleatorio", consejo);
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
