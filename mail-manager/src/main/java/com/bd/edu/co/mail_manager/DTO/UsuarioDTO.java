package com.bd.edu.co.mail_manager.DTO;


import java.util.Date;


public class UsuarioDTO {
    private String nombre;
    private String apellido;
    private String idEstado;
    private String idPais;
    private String correoAlterno;
    private String celular;
    private Date fechaNacimiento;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoAlterno() {
        return correoAlterno;
    }

    public void setCorreoAlterno(String correoAlterno) {
        this.correoAlterno = correoAlterno;
    }
}
