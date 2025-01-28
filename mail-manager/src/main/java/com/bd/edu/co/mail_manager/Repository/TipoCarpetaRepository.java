package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.TipoCarpeta;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCarpetaRepository extends JpaRepository<TipoCarpeta, String> {
    @Query(value = "SELECT * FROM TIPOCARPETA t WHERE t.IDTIPOCARPETA = :idtipocarpeta", nativeQuery = true)
    TipoCarpeta getTipoCarpetaByIdNative(@Param("idtipocarpeta") String idTipoCarpeta);

}
