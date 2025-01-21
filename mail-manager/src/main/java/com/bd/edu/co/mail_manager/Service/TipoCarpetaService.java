package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Entity.Estado;
import com.bd.edu.co.mail_manager.Entity.TipoCarpeta;
import com.bd.edu.co.mail_manager.Repository.TipoCarpetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoCarpetaService {

    @Autowired
    private TipoCarpetaRepository tipoCarpetaRepository;

    public TipoCarpeta getTipoCarpetaById(String id){
        return tipoCarpetaRepository.getTipoCarpetaByIdNative(id);
    }

    public TipoCarpeta getTipoCarpetaByName(String name){
        return tipoCarpetaRepository.getTipoCarpetaByName(name);
    }
}
