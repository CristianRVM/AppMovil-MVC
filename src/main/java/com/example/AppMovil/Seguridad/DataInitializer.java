package com.example.AppMovil.Seguridad;

import com.example.AppMovil.Entidad.Usuario;
import com.example.AppMovil.Respositorio.UsuarioRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/* Esta Clase configura un Usuario Tipo ADMIN al Iniciar el Proyecto */
@Component
public class DataInitializer implements CommandLineRunner {
    
    private UsuarioRepositorio usuarioDAO;
    private PasswordEncoder  passwordEncoder;
    
    // Insercion de Dependencias

    public DataInitializer(UsuarioRepositorio usuarioDAO, PasswordEncoder  passwordEncoder) {
        this.usuarioDAO = usuarioDAO;
        this.passwordEncoder = passwordEncoder;
    }
    

    @Override
    public void run(String... args) throws Exception {
        if(usuarioDAO.countByRol("ADMIN") == 0){
            Usuario admin = new Usuario();
            
            admin.setNombre("Admin");
            admin.setContrasenia(passwordEncoder.encode("admin123"));
            admin.setRol("ADMIN");
            admin.setEstado(true);
            
            usuarioDAO.save(admin);
            System.out.println("✅ Usuario ADMIN creado");
        }
    }
    
}
