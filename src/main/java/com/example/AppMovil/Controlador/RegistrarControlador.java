package com.example.AppMovil.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrarControlador {

    @GetMapping("/registrar")
    public String mostrarRegistrar() {
        return "registrar"; // carga registrar.html
    }

    @PostMapping("/registrar")
    public String procesarRegistrar(String nombre, String correo, String contrasena) {
        // Por ahora, simulamos el registro
        System.out.println("Usuario registrado: " + nombre + " - " + correo);
        
        // En el futuro aquí se guardará en BD
        return "redirect:/login"; // vuelve al login después del registro
    }
}
