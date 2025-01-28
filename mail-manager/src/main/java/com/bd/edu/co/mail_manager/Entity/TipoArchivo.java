package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TIPOARCHIVO")
public class TipoArchivo {
    @Id
    @Column(name = "IDTIPOARCHIVO", length = 5, nullable = false)
    private String idTipoArchivo;
    @Column(name = "DESCTIPOARCHIVO", length = 30, nullable = false)
    private String DescTipoArchivo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoArchivo")
    @JsonManagedReference
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
