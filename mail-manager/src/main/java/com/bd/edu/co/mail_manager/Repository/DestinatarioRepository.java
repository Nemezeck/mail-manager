package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.Destinatario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinatarioRepository extends JpaRepository<Destinatario, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO DESTINATARIO(CONSECCONTACTO, IDMENSAJE, USUARIO, IDPAIS, IDTIPOCOPIA)" +
            " VALUES (:conseccontacto, :idmensaje, :usuario, :idpais, :idtipocopia)", nativeQuery = true)
    void insertDestinatario(@Param("conseccontacto") Integer consecContacto,
                                    @Param("idmensaje") String messageId,
                                    @Param("usuario") String username,
                                    @Param("idpais") String countryId,
                                    @Param("idtipocopia") String idType);
}
