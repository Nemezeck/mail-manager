package com.bd.edu.co.mail_manager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MENSAJE")
public class Mensaje {
    @EmbeddedId
    private MensajePK mensajePK;

    @ManyToOne
    @MapsId("usuario")
    @JoinColumn(name = "USUARIO", referencedColumnName = "USUARIO", insertable = false, updatable = false)
    private Usuario user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "MEN_IDMENSAJE", referencedColumnName = "IDMENSAJE", insertable = false, updatable = false),
            @JoinColumn(name = "MEN_USUARIO", referencedColumnName = "USUARIO", insertable = false, updatable = false)
    })
    @NotFound(action=NotFoundAction.IGNORE)
    private Mensaje parentMessage;

    @ManyToOne
    @JoinColumn(name = "IDTIPOCARPETA", nullable = false)
    @JsonBackReference
    private TipoCarpeta tipoCarpeta;
    @ManyToOne
    @JoinColumn(name = "IDPAIS", nullable = false)
    private Pais pais;
    @Column(name = "ASUNTO", length = 255, nullable = false)
    private String asunto;
    @Column(name = "CUERPOMENSAJE", length = 255, nullable = false)
    private String cuerpoMensaje;
    @Column(name = "FECHAACCION", nullable = false)
    private Date fechaAccion;
    @Column(name = "HORAACCION", nullable = false)
    private Time horaAccion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mensaje")
    @JsonManagedReference
    private List<ArchivoAdjunto> archivos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mensaje")
    private List<Destinatario> destinatarios;

    public MensajePK getMensajePK() {
        return mensajePK;
    }

    public void setMensajePK(MensajePK mensajePK) {
        this.mensajePK = mensajePK;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Mensaje getParentMessage() {
        return parentMessage;
    }

    public void setParentMessage(Mensaje parentMessage) {
        this.parentMessage = parentMessage;
    }

    public TipoCarpeta getTipoCarpeta() {
        return tipoCarpeta;
    }

    public void setTipoCarpeta(TipoCarpeta tipoCarpeta) {
        this.tipoCarpeta = tipoCarpeta;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

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

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public Time getHoraAccion() {
        return horaAccion;
    }

    public void setHoraAccion(Time horaAccion) {
        this.horaAccion = horaAccion;
    }

    public List<ArchivoAdjunto> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ArchivoAdjunto> archivos) {
        this.archivos = archivos;
    }
}
