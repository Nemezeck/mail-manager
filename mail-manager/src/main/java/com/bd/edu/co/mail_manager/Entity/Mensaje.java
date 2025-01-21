package com.bd.edu.co.mail_manager.Entity;

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

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "MEN_IDMENSAJE", referencedColumnName = "IDMENSAJE", insertable = false, updatable = false),
            @JoinColumn(name = "MEN_USUARIO", referencedColumnName = "USUARIO", insertable = false, updatable = false)
    })
    private Mensaje parentMessage;

    @ManyToOne
    @JoinColumn(name = "IDTIPOCARPETA", nullable = false)
    private TipoCarpeta tipoCarpeta;
    @ManyToOne
    @JoinColumn(name = "IDPAIS", nullable = false)
    private Pais pais;
    @Column(name = "ASUNTO")
    private String asunto;
    @Column(name = "CUERPOMENSAJE")
    private String cuerpoMensaje;
    @Column(name = "FECHAACCION")
    private Date fechaAccion;
    @Column(name = "HORAACCION")
    private Time horaAccion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mensaje")
    private List<ArchivoAdjunto> archivos;
}
