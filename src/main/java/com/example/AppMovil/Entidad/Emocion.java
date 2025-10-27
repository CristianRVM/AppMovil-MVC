package com.example.AppMovil.Entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Emocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmocion;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String emoji;

    
    // Getters & Setters
    public Integer getIdEmocion() {
        return idEmocion;
    }

    public void setIdEmocion(Integer idEmocion) {
        this.idEmocion = idEmocion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
    
}
