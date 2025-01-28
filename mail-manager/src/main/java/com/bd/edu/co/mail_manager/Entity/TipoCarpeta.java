package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TIPOCARPETA")
public class TipoCarpeta {
    @Id
    @Column(name = "IDTIPOCARPETA", length = 5, nullable = false)
    private String idTipoCarpeta;
    @Column(name = "DESCTIPOCARPETA", length = 30, nullable = false)
    private String descTipoCarpeta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCarpeta")
    @JsonManagedReference
    private List<Mensaje> mensajes;

    public void setIdTipoCarpeta(String idTipoCarpeta) {
        this.idTipoCarpeta = idTipoCarpeta;
    }

    public String getDescTipoCarpeta() {
        return descTipoCarpeta;
    }

    public void setDescTipoCarpeta(String descTipoCarpeta) {
        this.descTipoCarpeta = descTipoCarpeta;
    }

    public String getIdTipoCarpeta() {
        return idTipoCarpeta;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
