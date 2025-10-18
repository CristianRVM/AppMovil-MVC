package com.example.AppMovil.Utilidades;

import com.example.AppMovil.Entidad.Usuario;
import com.example.AppMovil.Respositorio.UsuarioRepositorio;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VerificarConexion {

    @Autowired
    UsuarioRepositorio clienteRepositorio;

    @PostConstruct
    public void verificar() {
        try {
            List<Usuario> clientes = clienteRepositorio.findAll();
            System.out.println("\nMostrando Datos de Prueba Tabla Cliente");
            System.out.println("================================================");
            clientes.forEach(e
                    -> System.out.println("ID: " + e.getId()
                            + " Nombre: " + e.getNombre())
            );

            System.out.println("\nConexion a la BASE DE DATOS ESTABLECIDA\n");
        } catch (Exception e) {
            System.err.println("ERROR al verificar conexión:");
            e.printStackTrace();
        }
    }
}