package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "DESTINATARIO")
@JsonIgnoreProperties({"contacto"})
public class Destinatario {

    @Id
    @Column(name = "CONSECDESTINATARIO")
    private Integer consecDestinatario;

    @ManyToOne
    @JoinColumn(name = "CONSECCONTACTO")
    @JsonBackReference
    private Contacto contacto;

    @ManyToOne
    @JoinColumn(name = "IDTIPOCOPIA", nullable = false)
    @JsonBackReference
    private TipoCopia tipoCopia;

    @ManyToOne
    @JoinColumn(name = "IDPAIS", nullable = false)
    @JsonBackReference
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "IDMENSAJE", nullable = false)
    @JoinColumn(name = "USUARIO", nullable = false)
    @JsonBackReference
    private Mensaje mensaje;

    public Integer getConsecDestinatario() {
        return consecDestinatario;
    }

    public void setConsecDestinatario(Integer consecDestinatario) {
        this.consecDestinatario = consecDestinatario;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public TipoCopia getTipoCopia() {
        return tipoCopia;
    }

    public void setTipoCopia(TipoCopia tipoCopia) {
        this.tipoCopia = tipoCopia;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

}
