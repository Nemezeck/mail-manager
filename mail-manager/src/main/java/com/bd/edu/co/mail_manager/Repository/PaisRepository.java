package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, String> {

    @Query(value = "SELECT * FROM PAIS WHERE IDPAIS =:idpais", nativeQuery = true)
    public Pais getPaisByIdNative(@Param("idpais") String idPais);
}
