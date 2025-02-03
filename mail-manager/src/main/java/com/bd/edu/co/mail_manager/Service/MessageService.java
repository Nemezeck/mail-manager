package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.DTO.MensajeRequestDTO;
import com.bd.edu.co.mail_manager.Entity.*;
import com.bd.edu.co.mail_manager.Repository.DestinatarioRepository;
import com.bd.edu.co.mail_manager.Repository.MessageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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
    @Autowired
    private ContactoService contactoService;
    @Autowired
    private TipoCopiaService tipoCopiaService;
    @Autowired
    private DestinatarioRepository destinatarioRepository;
    @PersistenceContext
    private EntityManager entityManager;

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

    public ArchivoAdjunto addAttachments(String messageID, String username, String attachmentIDType, String attachmentName) {
        Mensaje m = messageRepository.getMensajeByIdAndUser(messageID, username);
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

    @Transactional
    public Map<String, List<String>> sendMessage(String messageId, String username, List<String> mailContactos, String idType){

        try {
            Mensaje message = messageRepository.getMensajeByIdAndUser(messageId, username);

            if(message == null){
                throw new RuntimeException("No message found");
            }

            List<Contacto> contactos = contactoService.getContactosByMail(mailContactos);


            if(contactos.isEmpty()){
                throw new RuntimeException("No contacts found");
            }

            TipoCopia tipoCopia = tipoCopiaService.findById(idType);

            if(tipoCopia == null){
                throw new RuntimeException("No tipocopia found");
            }

            List<Destinatario> destinatarios = new ArrayList<>();

            for(Contacto c : contactos){
                Destinatario d = new Destinatario();
                d.setMensaje(message);
                d.setContacto(c);
                d.setPais(message.getPais());
                d.setTipoCopia(tipoCopia);
                destinatarioRepository.insertDestinatario(d.getContacto().getConsecContacto(),
                        d.getMensaje().getMensajePK().getIdMensaje(),
                        d.getMensaje().getMensajePK().getUsuario(),
                        d.getMensaje().getPais().getIdPais(),
                        idType);
                destinatarios.add(d);
            }

            Map<String, List<String>> m = new HashMap<>();
            m.put("messageid", List.of(messageId));
            m.put("destinatarios", getAllMails(destinatarios));
            updateTipoCarpeta("Env", messageId, username);
            duplicateMessage(message, destinatarios);

            return m;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateTipoCarpeta(String value, String id, String username){
        messageRepository.updateMensaje(value, id, username);
    }

    public void duplicateMessage(Mensaje m, List<Destinatario> destinatarios) {
        for (Destinatario d : destinatarios) {
            Mensaje sentMessage = new Mensaje();
            sentMessage.setAsunto(m.getAsunto());
            sentMessage.setPais(d.getPais());

            MensajePK mensajePK = new MensajePK();
            mensajePK.setIdMensaje(m.getMensajePK().getIdMensaje());
            mensajePK.setUsuario(userService.findByMail(d.getContacto().getCorreoContacto()).getUsuario());

            sentMessage.setMensajePK(mensajePK);
            sentMessage.setFechaAccion(Date.valueOf(LocalDate.now()));
            sentMessage.setHoraAccion(Time.valueOf(LocalTime.now()));
            sentMessage.setCuerpoMensaje(m.getCuerpoMensaje());
            sentMessage.setUser(userService.findByMail(d.getContacto().getCorreoContacto()));
            sentMessage.setTipoCarpeta(tipoCarpetaService.getTipoCarpetaById("Rec"));

            messageRepository.insertMensajeNoParent(
                    sentMessage.getMensajePK().getIdMensaje(),
                    sentMessage.getMensajePK().getUsuario(),
                    sentMessage.getAsunto(),
                    sentMessage.getCuerpoMensaje(),
                    sentMessage.getFechaAccion(),
                    sentMessage.getHoraAccion(),
                    sentMessage.getPais().getIdPais(),
                    sentMessage.getTipoCarpeta().getIdTipoCarpeta()
            );

            sentMessage = messageRepository.getMensajeByIdAndUser(mensajePK.getIdMensaje(), mensajePK.getUsuario());

            if (m.getArchivos() != null && !m.getArchivos().isEmpty()) {
                for (ArchivoAdjunto ad : m.getArchivos()) {
                    addAttachments(sentMessage.getMensajePK().getIdMensaje(), sentMessage.getMensajePK().getUsuario(), ad.getTipoArchivo().getIdTipoArchivo(), ad.getNomArchivo());
                }
            }
        }
    }


    public List<String> getAllMails(List<Destinatario> destinatarios){
        List<String> mails = new ArrayList<>();
        if (!destinatarios.isEmpty()) {
            for(Destinatario d : destinatarios){
                mails.add(d.getContacto().getCorreoContacto());
            }
            return mails;
        }
        else{
            throw new RuntimeException("No mails found");
        }
    }
}
