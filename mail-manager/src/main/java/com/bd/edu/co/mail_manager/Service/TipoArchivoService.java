package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Entity.TipoArchivo;
import com.bd.edu.co.mail_manager.Repository.TipoArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoArchivoService {

    @Autowired
    private TipoArchivoRepository repository;

    public TipoArchivo getTipoArchivoByIdNative(String id){
        return repository.getTipoArchivoById(id);
    }
}
