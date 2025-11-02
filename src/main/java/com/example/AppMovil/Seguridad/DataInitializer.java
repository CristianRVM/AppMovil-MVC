package com.example.AppMovil.Seguridad;

import com.example.AppMovil.Entidad.Usuario;
import com.example.AppMovil.Respositorio.UsuarioRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/* Esta Clase configura un Usuario Tipo ADMIN al Iniciar el Proyecto */
@Component
public class DataInitializer implements CommandLineRunner {
    
    private UsuarioRepositorio usuarioDAO;
    private PasswordEncoder  passwordEncoder;
    
    public DataInitializer(UsuarioRepositorio usuarioDAO, PasswordEncoder  passwordEncoder) {
        this.usuarioDAO = usuarioDAO;
        this.passwordEncoder = passwordEncoder;
    }
    

    @Override
    public void run(String... args) throws Exception {
        if(usuarioDAO.countByRol("USER") == 0){
            Usuario admin = new Usuario();
            
            admin.setNombre("UsuarioPrueba");
            admin.setCorreo("test@example.com");
            admin.setContrasenia(passwordEncoder.encode("123"));
            admin.setRol("USER");
            admin.setEstado(true);
            
            usuarioDAO.save(admin);
            System.out.println("Usuario de Prueba creado");
        }
    }
    
}
