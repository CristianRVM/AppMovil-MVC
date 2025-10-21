package com.example.AppMovil.Respositorio;

import com.example.AppMovil.Entidad.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{

    List<Usuario> getByEstadoTrue(); //SELECT * FROM usuario WHERE estado = 1
    
    Usuario findByNombre(String Nombre); //SELECT * FROM usuario WHERE nombre = ? 
    
    Usuario findByCorreo(String correo); //SELECT * FROM usuario WHERE correo = ?
    
    boolean existsByCorreo(String correo); // Verificar duplicados por correo
    
    long countByRol(String rol); //SELECT COUNT(*) FROM usuario WHERE rol = "rol"
}
