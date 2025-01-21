package com.bd.edu.co.mail_manager.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIPOCOPIA")
public class TipoCopia {

    @Id
    @Column(name = "IDTIPOCOPIA")
    private String idTipoCopia;
    @Column(name = "DESCTIPOCOPIA")
    private String descTipoCopia;

    public String getIdTipoCopia() {
        return idTipoCopia;
    }

    public void setIdTipoCopia(String idTipoCopia) {
        this.idTipoCopia = idTipoCopia;
    }

    public String getDescTipoCopia() {
        return descTipoCopia;
    }

    public void setDescTipoCopia(String descTipoCopia) {
        this.descTipoCopia = descTipoCopia;
    }
}
