package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.DTO.MensajeRequestDTO;
import com.bd.edu.co.mail_manager.Entity.*;
import com.bd.edu.co.mail_manager.Repository.DestinatarioRepository;
import com.bd.edu.co.mail_manager.Repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    private MensajeRepositoryCustom repositoryCustom;

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

    public Map<?, ?> getAllMessagesByType(String idTipoCarpeta, String username) {
        if (idTipoCarpeta.equals("Rec")) {
            List<Mensaje> mensajes = messageRepository.getAllMailsByType(username, idTipoCarpeta);
            Map<String, Mensaje> map = new HashMap<>();
            for (Mensaje m : mensajes) {
                String sender = messageRepository.getSender(m.getMensajePK().getIdMensaje());
                map.put(sender, m);
            }
            return map;
        } else if (idTipoCarpeta.equals("Env")) {
            List<Mensaje> mensajes = messageRepository.getAllMailsByType(username, idTipoCarpeta);
            Map<List<String>, Mensaje> map = new HashMap<>();
            for (Mensaje m : mensajes) {
                List<String> recipients = getAllMails(m.getDestinatarios());
                map.put(recipients, m);
            }
            return map;
        } else if (idTipoCarpeta.equals("Bor")) {
            List<Mensaje> mensajes = messageRepository.getAllMailsByType(username, idTipoCarpeta);
            Map<String, Mensaje> map = new HashMap<>();
            for (Mensaje m : mensajes) {
                map.put(m.getMensajePK().getIdMensaje(), m);
            }
            return map;
        }

        throw new IllegalArgumentException("Invalid folder type: " + idTipoCarpeta);
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
        archivoAdjuntoService.insertArchivoAdjunto(archivoAdjunto);

        return archivoAdjunto;
    }

    public String removeAttachment(String messageID, String username, String attachmentIDType, String attachmentName){
        String deletedAttachment = "Deleted: " + attachmentName;

        archivoAdjuntoService.deleteArchivoAdjunto(messageID, username, attachmentIDType, attachmentName);

        return deletedAttachment;
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

    @Transactional
    public String updateMensaje(String idMensaje, String usuario, Map<String, Object> updateFields) {
        return repositoryCustom.updateMensaje(idMensaje, usuario, updateFields);
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

    @Transactional
    public Map<String, List<String>> forwardMessage(String messageId, String senderUsername, List<String> mailContactos, String idType, String username, String newBody, List<ArchivoAdjunto> newAttachments) {

        try {
            Mensaje parentMessage = messageRepository.getMensajeByIdAndUser(messageId, senderUsername);

            if(parentMessage == null){
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

            Usuario forwarderUser = userService.getUsuarioById(username);
            if(forwarderUser == null ){
                throw new RuntimeException("No user found");
            }

            Mensaje newMessage = createForwardMessage(parentMessage, newBody, username, forwarderUser.getPais().getIdPais());

            if (newAttachments != null && !newAttachments.isEmpty()) {
                for (ArchivoAdjunto ad : newAttachments) {
                    addAttachments(newMessage.getMensajePK().getIdMensaje(), newMessage.getMensajePK().getUsuario(), ad.getTipoArchivo().getIdTipoArchivo(), ad.getNomArchivo());
                }
            }

            List<Destinatario> destinatarios = new ArrayList<>();
            for(Contacto c : contactos){
                Destinatario d = new Destinatario();
                d.setMensaje(newMessage);
                d.setContacto(c);
                d.setPais(newMessage.getPais());
                d.setTipoCopia(tipoCopia);
                destinatarioRepository.insertDestinatario(d.getContacto().getConsecContacto(),
                        d.getMensaje().getMensajePK().getIdMensaje(),
                        d.getMensaje().getMensajePK().getUsuario(),
                        d.getMensaje().getPais().getIdPais(),
                        idType);
                destinatarios.add(d);
            }

            Map<String, List<String>> m = new HashMap<>();
            m.put("messageid", getMessageChainIds(newMessage));
            m.put("destinatarios", getAllMails(destinatarios));

            updateTipoCarpeta("Env", messageId, senderUsername);

            duplicateMessageWithParent(parentMessage, destinatarios);

            return m;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public Mensaje createForwardMessage(Mensaje m, String newBody, String username, String idPais) {
        if (m == null) {
            throw new IllegalArgumentException("Cannot forward a null message");
        }

        String subject = "FORWARDED: " + m.getAsunto();

        MensajeRequestDTO dto = new MensajeRequestDTO();
        dto.setAsunto(subject);
        dto.setCuerpoMensaje(newBody);
        dto.setUsuario(username);
        dto.setTipoCarpeta("Env");
        dto.setIdPais(idPais);

        Mensaje forwardedMessage = createMensaje(dto);
        forwardedMessage.setParentMessage(m);

        if (m.getArchivos() != null && !m.getArchivos().isEmpty()) {
            for (ArchivoAdjunto ad : m.getArchivos()) {
                addAttachments(forwardedMessage.getMensajePK().getIdMensaje(),
                        forwardedMessage.getMensajePK().getUsuario(),
                        ad.getTipoArchivo().getIdTipoArchivo(),
                        ad.getNomArchivo());
            }
        }

        return forwardedMessage;
    }

    public List<String> getMessageChainIds(Mensaje mensaje) {
        List<String> messageIds = new ArrayList<>();
        while (mensaje.getParentMessage() != null) {
            mensaje = mensaje.getParentMessage();
            messageIds.add(mensaje.getMensajePK().getIdMensaje());
        }
        return messageIds;
    }

    public void duplicateMessageWithParent (Mensaje m, List<Destinatario> destinatarios) {
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
            sentMessage.setParentMessage(m);

            messageRepository.insertMensajeWithParent(
                    sentMessage.getMensajePK().getIdMensaje(),
                    sentMessage.getMensajePK().getUsuario(),
                    sentMessage.getAsunto(),
                    sentMessage.getCuerpoMensaje(),
                    sentMessage.getFechaAccion(),
                    sentMessage.getHoraAccion(),
                    sentMessage.getPais().getIdPais(),
                    sentMessage.getTipoCarpeta().getIdTipoCarpeta(),
                    sentMessage.getParentMessage().getMensajePK().getIdMensaje(),
                    sentMessage.getParentMessage().getMensajePK().getUsuario()
            );

            sentMessage = messageRepository.getMensajeByIdAndUser(mensajePK.getIdMensaje(), mensajePK.getUsuario());

            if (m.getArchivos() != null && !m.getArchivos().isEmpty()) {
                for (ArchivoAdjunto ad : m.getArchivos()) {
                    addAttachments(sentMessage.getMensajePK().getIdMensaje(), sentMessage.getMensajePK().getUsuario(), ad.getTipoArchivo().getIdTipoArchivo(), ad.getNomArchivo());
                }
            }
        }
    }
}
