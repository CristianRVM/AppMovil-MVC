package com.example.AppMovil.Controlador;

import com.example.AppMovil.DTO.RegistroDTO;
import com.example.AppMovil.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrarControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("form", new RegistroDTO());
        return "registrar";
    }

    @PostMapping("/registrar") // <-- asegura la barra inicial
    public String registrarUsuario(@ModelAttribute("form") RegistroDTO form, Model model) {
        // ✅ Validación simple
        if (form.getNombre() == null || form.getNombre().isBlank()
                || form.getCorreo() == null || form.getCorreo().isBlank()
                || form.getPassword() == null || form.getPassword().isBlank()
                || form.getConfirmPassword() == null || form.getConfirmPassword().isBlank()) {
            model.addAttribute("error", "Completa todos los campos.");
            return "registrar";
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "registrar";
        }

        try {
            usuarioServicio.registrarNuevo(form.getNombre(), form.getCorreo(), form.getPassword());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // "El correo ya está registrado"
            return "registrar";
        }

        return "redirect:/login?registered";
    }
}
