package com.example.AppMovil.Servicio;

import com.example.AppMovil.Entidad.Usuario;
import com.example.AppMovil.Respositorio.UsuarioRepositorio;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImp implements UserDetailsService {
    
    private final UsuarioRepositorio usuarioDAO;
    
    public UserDetailsServiceImp(UsuarioRepositorio usuarioDAO){
        this.usuarioDAO = usuarioDAO;
    }
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findByCorreo(email); // SQL Busca por Correo
        
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        
        if (!usuario.getEstado()){
            throw new UsernameNotFoundException("Usuario está inactivo");
        }
        
        return User.builder()
                .username(email) // Utiliza correo como username
                .password(usuario.getContrasenia())
                .roles(usuario.getRol()) // ADMIN, USER
                .build();
    }
    
}
