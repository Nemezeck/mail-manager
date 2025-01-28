package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Entity.Estado;
import com.bd.edu.co.mail_manager.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    public Estado getEstadoById(String id){
        return estadoRepository.getReferenceById(id);
    }
}
