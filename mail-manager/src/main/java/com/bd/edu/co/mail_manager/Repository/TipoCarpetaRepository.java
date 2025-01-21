package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.Pais;
import com.bd.edu.co.mail_manager.Entity.TipoCarpeta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoCarpetaRepository extends JpaRepository<TipoCarpeta, String> {
    @Query(value = "SELECT * FROM TIPOCARPETA t WHERE t.IDTIPOCARPETA = ?", nativeQuery = true)
    public TipoCarpeta getTipoCarpetaByIdNative(@Param("idtipocarpeta") String idTipoCarpeta);

    @Query("SELECT t FROM TipoCarpeta t WHERE t.descTipoCarpeta = :nombre")
    public TipoCarpeta getTipoCarpetaByName(@Param("nombre") String nombre);
}
