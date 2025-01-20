package com.bd.edu.co.mail_manager.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PAIS")

public class Pais {

    @Column(name = "ID")
    private Integer id;

    @Id
    @Column(name = "IDPAIS")
    private String idPais;
    @Column(name = "NOMBREPAIS")
    private String nombrePais;
    @Column(name = "DOMINIO")
    private String dominio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    private List<Usuario> usuarios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
