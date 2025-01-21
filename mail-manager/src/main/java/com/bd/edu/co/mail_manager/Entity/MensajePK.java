package com.bd.edu.co.mail_manager.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class MensajePK implements Serializable {
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "IDMENSAJE")
    private String idMensaje;

}
