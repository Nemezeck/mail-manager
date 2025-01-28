package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.DTO.MensajeRequestDTO;
import com.bd.edu.co.mail_manager.Entity.ArchivoAdjunto;
import com.bd.edu.co.mail_manager.Entity.Mensaje;
import com.bd.edu.co.mail_manager.Entity.MensajePK;
import com.bd.edu.co.mail_manager.Entity.TipoArchivo;
import com.bd.edu.co.mail_manager.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
    @Autowired
    private TipoArchivoService archivoService;
    @Autowired
    private ArchivoAdjuntoService archivoAdjuntoService;

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
        mensaje.setTipoCarpeta(tipoCarpetaService.getTipoCarpetaById(requestDTO.getTipoCarpeta()));
        mensaje.setUser(userService.getUsuarioById(requestDTO.getUsuario()));
        messageRepository.insertMensajeNoParent(mensaje.getMensajePK().getIdMensaje(), mensaje.getMensajePK().getUsuario(), mensaje.getAsunto(), mensaje.getCuerpoMensaje(),
                mensaje.getFechaAccion(), mensaje.getHoraAccion(), mensaje.getPais().getIdPais(), mensaje.getTipoCarpeta().getIdTipoCarpeta());
        return mensaje;
    }

    public List<Mensaje> getAllMessagesByType(String idTipoCarpeta, String username){
        return messageRepository.getAllMailsByType(username, idTipoCarpeta);
    }

    public Mensaje loadMessageById(String messageID){
        return messageRepository.getMensajeById(messageID);
    }

    public ArchivoAdjunto addAttachments(String messageID, String attachmentIDType, String attachmentName) {
        Mensaje m = loadMessageById(messageID);
        ArchivoAdjunto archivoAdjunto = new ArchivoAdjunto();
        TipoArchivo ta = archivoService.getTipoArchivoByIdNative(attachmentIDType);

        String newAttachmentName = attachmentName;

        if (!m.getArchivos().isEmpty()) {
            String finalNewAttachmentName = newAttachmentName;
            while (m.getArchivos().stream().anyMatch(archivoAdjunto1 -> archivoAdjunto1.getNomArchivo().equals(finalNewAttachmentName))) {
                newAttachmentName = createAttachmentName(newAttachmentName);
            }
        }

        archivoAdjunto.setNomArchivo(newAttachmentName);
        archivoAdjunto.setTipoArchivo(ta);
        archivoAdjunto.setMensaje(m);
        archivoAdjuntoService.insertArchivoPago(archivoAdjunto);

        return archivoAdjunto;
    }

    private String createAttachmentName(String attachmentName){
        if(!attachmentName.isEmpty()){
            if(Character.isDigit(attachmentName.charAt(attachmentName.length()-1))){
                int i = Character.getNumericValue(attachmentName.charAt(attachmentName.length()-1)) + 1;
                return attachmentName + i;
            }else{
                return attachmentName + "1";
            }
        }else {
            return "AttachmentName";
        }
    }

    private String generateId() {
        Random random = new Random();

        char firstChar = (char) ('A' + random.nextInt(26));

        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            digits.append(random.nextInt(10));
        }

        char lastChar = (char) ('a' + random.nextInt(26));

        return firstChar + digits.toString() + lastChar;
    }
}
