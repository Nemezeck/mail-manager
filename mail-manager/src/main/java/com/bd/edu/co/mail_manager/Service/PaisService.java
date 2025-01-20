package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Entity.Pais;
import com.bd.edu.co.mail_manager.Repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {
    @Autowired
    private PaisRepository paisRepository;

    public Pais getPaisById(String id){
        return paisRepository.getPaisByIdNative(id);
    }
}
