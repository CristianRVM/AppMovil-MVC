package com.example.AppMovil.Entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Consejo")
public class Consejo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsejo;

    @Column(nullable = false, length = 255)
    private String contenido;

    public Long getIdConsejo() {
        return idConsejo;
    }

    public void setIdConsejo(Long idConsejo) {
        this.idConsejo = idConsejo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
