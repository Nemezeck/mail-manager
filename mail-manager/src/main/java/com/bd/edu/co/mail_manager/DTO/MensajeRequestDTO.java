package com.bd.edu.co.mail_manager.DTO;

public class MensajeRequestDTO {

    private String asunto;
    private String cuerpoMensaje;
    private String tipoCarpeta;
    private String usuario;
    private String idPais;
    private String idCategoria;

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public String getTipoCarpeta() {
        return tipoCarpeta;
    }

    public void setTipoCarpeta(String tipoCarpeta) {
        this.tipoCarpeta = tipoCarpeta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }


    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }
}
