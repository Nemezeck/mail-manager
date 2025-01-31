package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Entity.TipoCopia;
import com.bd.edu.co.mail_manager.Repository.TipoCopiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoCopiaService {
    @Autowired
    private TipoCopiaRepository tipoCopiaRepository;

    public TipoCopia findById(String idType){
        return tipoCopiaRepository.findByIdNative(idType);
    }
}
