package com.bd.edu.co.mail_manager.Entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "ESTADO")
public class Estado {

    @Column(name = "ID")
    private Integer id;

    @Id
    @Column(name = "IDESTADO")
    private String idEstado;
    @Column(name = "NOMBREESTADO")
    private String nombreEstado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private List<Usuario> usuarios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
