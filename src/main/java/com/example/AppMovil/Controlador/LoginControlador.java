package com.example.AppMovil.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControlador {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String usuario,
            @RequestParam String contrasena,
            Model model) {

        // Validación simulada
        if ("admin".equals(usuario) && "1234".equals(contrasena)) {
            return "redirect:/home"; // redirige al home
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login"; // recarga el login con el mensaje
        }
    }
}
