package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.TipoCopia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCopiaRepository extends JpaRepository<TipoCopia, String> {

    @Query(value = "SELECT * FROM TIPOCOPIA TP WHERE TP.IDTIPOCOPIA = :idtipocopia", nativeQuery = true)
    TipoCopia findByIdNative(@Param("idtipocopia") String idType);
}
