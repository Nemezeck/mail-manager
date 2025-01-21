package com.bd.edu.co.mail_manager.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TIPOCARPETA")
public class TipoCarpeta {

    @Id
    @Column(name = "IDTIPOCARPETA")
    private String idTipoCarpeta;
    @Column(name = "DESCTIPOCARPETA")
    private String descTipoCarpeta;
    @Column(name = "ID")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoCarpeta")
    private List<Mensaje> mensajes;

    public String getIdTipoCarpeta() {
        return idTipoCarpeta;
    }

    public void setIdTipoCarpeta(String idTipoCarpeta) {
        this.idTipoCarpeta = idTipoCarpeta;
    }

    public String getDescTipoCarpeta() {
        return descTipoCarpeta;
    }

    public void setDescTipoCarpeta(String descTipoCarpeta) {
        this.descTipoCarpeta = descTipoCarpeta;
    }
}
