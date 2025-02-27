package com.bd.edu.co.mail_manager.Repository;


import com.bd.edu.co.mail_manager.Entity.Mensaje;
import com.bd.edu.co.mail_manager.Entity.MensajePK;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Mensaje, MensajePK> {

    @Transactional
    @Modifying
    @Query(value = "insert into mensaje (idmensaje, usuario, asunto, cuerpomensaje, fechaaccion, horaaccion, idpais, idtipocarpeta) " +
            "values (:idmensaje, :usuario, :asunto, :cuerpomensaje, :fechaaccion, :horaaccion, :idpais, :idtipocarpeta)",
            nativeQuery = true)
    void insertMensajeNoParent(@Param("idmensaje") String idmensaje,
                               @Param("usuario") String usuario,
                               @Param("asunto") String asunto,
                               @Param("cuerpomensaje") String cuerpomensaje,
                               @Param("fechaaccion") Date fechaaccion,
                               @Param("horaaccion") Time horaaccion,
                               @Param("idpais") String idpais,
                               @Param("idtipocarpeta") String idtipocarpeta);

    @Query(value = "select * from mensaje m where m.usuario=:usuario and m.idtipocarpeta=:idtipocarpeta", nativeQuery = true)
    List<Mensaje> getAllMailsByType(@Param("usuario") String username, @Param("idtipocarpeta") String idType);

    @Query(value = "select * from mensaje m where m.idmensaje=:idmensaje", nativeQuery = true)
    Mensaje getMensajeById(@Param("idmensaje") String messageID);

}
