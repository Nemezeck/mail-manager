package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USUARIO")

public class Usuario {
    @Column(name= "ID")
    private Integer id;

    @Id
    @Column(name = "USUARIO")
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "IDESTADO", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "IDPAIS", nullable = false)
    @JsonBackReference
    private Pais pais;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Contacto> contactos;

    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "FECHANACIMIENTO")
    private Date fechaNacimiento;
    @Column(name = "FECHACREACION")
    private Date fechaCreacion;
    @Column(name = "CORREOALTERNO")
    private String correoAlterno;
    @Column(name = "CELULAR")
    private String celular;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Mensaje> mensajes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorreoAlterno() {
        return correoAlterno;
    }

    public void setCorreoAlterno(String correoAlterno) {
        this.correoAlterno = correoAlterno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
