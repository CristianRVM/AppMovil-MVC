package com.example.AppMovil.Entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
public class EstadoDeAnimo {
  @Id 
  @GeneratedValue(strategy=GenerationType.IDENTITY) 
  private Long idEstadoDeAnimo;
  
  @ManyToOne(optional=false) 
  @JoinColumn(name="idUsuario") 
  private Usuario usuario;
  
  @ManyToOne(optional=false) 
  @JoinColumn(name="idEmocion") 
  private Emocion emocion;
  
  @Column(nullable=false, length=200) 
  private String texto;
  
  @Column(nullable=false) 
  private LocalDateTime creadoEn = LocalDateTime.now();
  
    // Getters & Setters
    public Long getIdEstadoDeAnimo() {
        return idEstadoDeAnimo;
    }

    public void setIdEstadoDeAnimo(Long idEstadoDeAnimo) {
        this.idEstadoDeAnimo = idEstadoDeAnimo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Emocion getEmocion() {
        return emocion;
    }

    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
  
}

