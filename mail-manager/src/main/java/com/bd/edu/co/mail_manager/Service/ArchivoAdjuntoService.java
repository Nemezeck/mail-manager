package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Entity.ArchivoAdjunto;
import com.bd.edu.co.mail_manager.Repository.ArchivoAdjuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchivoAdjuntoService {
    @Autowired
    private ArchivoAdjuntoRepository repository;

    public void insertArchivoPago(ArchivoAdjunto a){
        repository.insertArchivoAdjunto(a.getNomArchivo(),
                a.getMensaje().getMensajePK().getIdMensaje(),
                a.getMensaje().getMensajePK().getUsuario(),
                a.getTipoArchivo().getIdTipoArchivo());
    }
}
