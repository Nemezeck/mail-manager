package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.ArchivoAdjunto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoAdjuntoRepository extends JpaRepository<ArchivoAdjunto, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into archivoadjunto(nomarchivo, idmensaje, usuario, idtipoarchivo) values (:nomarchivo, :idmensaje, :usuario, :idtipoarchivo)",
            nativeQuery = true)
    void insertArchivoAdjunto(@Param("nomarchivo") String attachmentName, @Param("idmensaje") String messageId, @Param("usuario") String username,
                              @Param("idtipoarchivo") String attachmentId);
}
