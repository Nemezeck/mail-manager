package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {
    @Id
    @Column(name = "IDCATEGORIA", length = 3)
    private String idCategoria;
    @Column(name = "DESCCATEGORIA", length = 40, nullable = false)
    private String descCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    @JsonManagedReference
    private List<Mensaje> mensajes;

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
