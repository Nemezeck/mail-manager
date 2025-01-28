package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.TipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoArchivoRepository extends JpaRepository<TipoArchivo, String> {

    @Query(value = "select * from tipoarchivo t where t.idtipoarchivo=:idtipoarchivo", nativeQuery = true)
    TipoArchivo getTipoArchivoById(@Param("idtipoarchivo") String attachmentID);
}
