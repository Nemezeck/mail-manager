package com.bd.edu.co.mail_manager.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TIPOARCHIVO")
public class TipoArchivo {
    @Id
    @Column(name = "IDTIPOARCHIVO")
    private String idTipoArchivo;
    @Column(name = "DESCTIPOARCHIVO")
    private String DescTipoArchivo;
    @Column(name = "ID")
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoArchivo")
    private List<ArchivoAdjunto> archivos;

    public String getIdTipoArchivo() {
        return idTipoArchivo;
    }

    public void setIdTipoArchivo(String idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
    }

    public String getDescTipoArchivo() {
        return DescTipoArchivo;
    }

    public void setDescTipoArchivo(String descTipoArchivo) {
        DescTipoArchivo = descTipoArchivo;
    }
}
