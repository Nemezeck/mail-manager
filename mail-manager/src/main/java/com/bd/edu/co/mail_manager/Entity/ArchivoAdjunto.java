package com.bd.edu.co.mail_manager.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ARCHIVOADJUNTO")
public class ArchivoAdjunto {

    @Id
    @Column(name = "CONSECARCHIVO")
    private Integer consecArchivo;
    @Column(name = "NOMARCHIVO")
    private String nomArchivo;

    @ManyToOne
    @JoinColumn(name = "IDTIPOARCHIVO", nullable = false)
    private TipoArchivo tipoArchivo;

    @ManyToOne
    @JoinColumn(name = "IDMENSAJE", nullable = false)
    @JoinColumn(name = "USUARIO", nullable = false)
    private Mensaje mensaje;
}
