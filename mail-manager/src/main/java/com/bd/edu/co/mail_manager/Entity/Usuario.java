package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USUARIO")

public class Usuario {
    @Id
    @Column(name = "USUARIO", length = 5, nullable = false)
    private String usuario;

    @ManyToOne
    @JoinColumn(name = "IDESTADO", nullable = false)
    @JsonBackReference
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "IDPAIS", nullable = false)
    @JsonBackReference
    private Pais pais;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonManagedReference
    private List<Contacto> contactos;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;
    @Column(name = "APELLIDO", length = 30, nullable = false)
    private String apellido;
    @Column(name = "FECHANACIMIENTO", nullable = false)
    private Date fechaNacimiento;
    @Column(name = "FECHACREACION", nullable = false)
    private Date fechaCreacion;
    @Column(name = "CORREOALTERNO", length = 30, nullable = false)
    private String correoAlterno;
    @Column(name = "CELULAR", length = 12, nullable = false)
    private String celular;

    @Column(name = "PASSWORD", length = 70, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Mensaje> mensajes;


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

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }
}

