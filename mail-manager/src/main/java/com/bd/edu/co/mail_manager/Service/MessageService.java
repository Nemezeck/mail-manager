package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.DTO.MensajeRequestDTO;
import com.bd.edu.co.mail_manager.Entity.Mensaje;
import com.bd.edu.co.mail_manager.Entity.MensajePK;
import com.bd.edu.co.mail_manager.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private PaisService paisService;
    @Autowired
    private TipoCarpetaService tipoCarpetaService;
    @Autowired
    private UserService userService;


    public Mensaje createMensaje(MensajeRequestDTO requestDTO){

        MensajePK mensajePK = new MensajePK();
        mensajePK.setIdMensaje(generateId());
        mensajePK.setUsuario(requestDTO.getUsuario());
        Mensaje mensaje = new Mensaje();
        mensaje.setMensajePK(mensajePK);
        mensaje.setCuerpoMensaje(requestDTO.getCuerpoMensaje());
        mensaje.setAsunto(requestDTO.getAsunto());
        mensaje.setPais(paisService.getPaisById(requestDTO.getIdPais()));
        mensaje.setFechaAccion(Date.valueOf(LocalDate.now()));
        mensaje.setHoraAccion(Time.valueOf(LocalTime.now()));
        mensaje.setTipoCarpeta(tipoCarpetaService.getTipoCarpetaByName(requestDTO.getTipoCarpeta()));

        return  mensaje;
    }



    private String generateId() {
        Random random = new Random();

        // Generate the first character (uppercase letter)
        char firstChar = (char) ('A' + random.nextInt(26));

        // Generate the next three characters (digits)
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            digits.append(random.nextInt(10));
        }

        // Generate the last character (lowercase letter)
        char lastChar = (char) ('a' + random.nextInt(26));

        // Combine all parts to form the ID
        return firstChar + digits.toString() + lastChar;
    }
}
