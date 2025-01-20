package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

    @Query(value = "SELECT * FROM ESTADO WHERE IDESTADO =:idestado", nativeQuery = true)
    public Estado getPaisByIdNative(@Param("idestado") String idEstado);
}
