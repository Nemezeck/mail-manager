package com.bd.edu.co.mail_manager.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CONTACTO")
public class Contacto {
    @Id
    @Column(name = "CONSECCONTACTO")
    private Integer consecContacto;
    @Column(name = "NOMBRECONTACTO", length = 30)
    private String nombreContacto;
    @Column(name = "CORREOCONTACTO", length = 30, nullable = false)
    private String correoContacto;

    @ManyToOne
    @JoinColumn(name = "USUARIO", nullable = false)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contacto")
    private List<Destinatario> destinatarios;

    public Integer getConsecContacto() {
        return consecContacto;
    }

    public void setConsecContacto(Integer consecContacto) {
        this.consecContacto = consecContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Destinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }
}
