package com.bd.edu.co.mail_manager.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MensajePK implements Serializable {
    @Column(name = "USUARIO" , length = 5, nullable = false)
    private String usuario;
    @Column(name = "IDMENSAJE", length = 5, nullable = false)
    private String idMensaje;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }
}
