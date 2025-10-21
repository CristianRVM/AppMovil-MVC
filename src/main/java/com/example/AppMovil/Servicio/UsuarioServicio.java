package com.example.AppMovil.Servicio;

import com.example.AppMovil.Entidad.Usuario;
import com.example.AppMovil.Respositorio.UsuarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioDAO;

    @Autowired
    private PasswordEncoder encoder;

    // Mostrar Todos los Registros
    public List<Usuario> getList() {
        return usuarioDAO.getByEstadoTrue();
    }

    // Guardar Un Registro
    public Usuario save(Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    // Obtener un Registro por id
    public Usuario get(Integer id) {
        return usuarioDAO.findById(id).orElse(null);
    }

    // Eliminar un Registro por id (Borrado Logico)
    public void delete(Integer id) {
        usuarioDAO.findById(id).ifPresent(u -> {
            u.setEstado(false);
            usuarioDAO.save(u);
        });
    }

    //Registrar con validaciones mínimas
    public Usuario registrarNuevo(String nombre, String correo, String rawPassword) {
        if (usuarioDAO.existsByCorreo(correo)) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setContrasenia(encoder.encode(rawPassword));
        u.setRol("USER");
        u.setEstado(true);
        return usuarioDAO.save(u);
    }
}
