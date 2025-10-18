package com.example.AppMovil.Respositorio;

import com.example.AppMovil.Entidad.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
 //NOTA: Existe Error en Config Seguridad porque 
    /* modifique la estructura de la clase usuario, en consecuencia tengo
    que modificar servicio, y repositorio queizas modificar el metodo que muestre 
    solo a los usuarios activos
    */
    
    List<Usuario> getByEstadoTrue(); //SELECT * FROM usuario WHERE estado = 1
    
    Usuario findByNombre(String Nombre); //SELECT * FROM usuario WHERE nombre = ? 
    
    long countByRol(String rol); //SELECT COUNT(*) FROM usuario WHERE rol = "rol"
}
