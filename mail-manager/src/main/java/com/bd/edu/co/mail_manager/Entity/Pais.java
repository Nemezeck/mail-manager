package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PAIS")
@JsonIgnoreProperties({"usuarios"})
public class Pais {

    @Id
    @Column(name = "IDPAIS", length = 5, nullable = false)
    private String idPais;
    @Column(name = "NOMBREPAIS", length = 30, nullable = false)
    private String nombrePais;
    @Column(name = "DOMINIO", length = 3, nullable = false)
    private String dominio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    @JsonManagedReference
    private List<Usuario> usuarios;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    private List<Mensaje> mensajes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pais")
    private List<Destinatario> destinatarios;

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
