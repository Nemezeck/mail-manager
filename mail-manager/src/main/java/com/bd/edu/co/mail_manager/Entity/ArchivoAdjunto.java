package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "ARCHIVOADJUNTO")
public class ArchivoAdjunto {

    @Id
    @Column(name = "CONSECARCHIVO")
    private Integer consecArchivo;
    @Column(name = "NOMARCHIVO", length = 30, nullable = false)
    private String nomArchivo;

    @ManyToOne
    @JoinColumn(name = "IDTIPOARCHIVO", nullable = false)
    @JsonBackReference
    private TipoArchivo tipoArchivo;

    @ManyToOne
    @JoinColumn(name = "IDMENSAJE", nullable = false)
    @JoinColumn(name = "USUARIO", nullable = false)
    @JsonBackReference
    private Mensaje mensaje;

    public Integer getConsecArchivo() {
        return consecArchivo;
    }

    public void setConsecArchivo(Integer consecArchivo) {
        this.consecArchivo = consecArchivo;
    }

    public String getNomArchivo() {
        return nomArchivo;
    }

    public void setNomArchivo(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }

    public TipoArchivo getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(TipoArchivo tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }
}
