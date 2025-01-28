package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "ESTADO")
public class Estado {
    @Id
    @Column(name = "IDESTADO", length = 5, nullable = false)
    private String idEstado;
    @Column(name = "NOMBREESTADO", length = 30, nullable = false)
    private String nombreEstado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    @JsonManagedReference
    private List<Usuario> usuarios;


    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
