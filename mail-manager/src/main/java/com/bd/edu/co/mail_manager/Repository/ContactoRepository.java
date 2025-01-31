package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.Contacto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO CONTACTO (CORREOCONTACTO, NOMBRECONTACTO, USUARIO) " +
            "VALUES (:correocontacto, :nombrecontacto, :usuario)", nativeQuery = true)
    void insertContacto(@Param("correocontacto") String correoContacto,
                            @Param("nombrecontacto") String nombreContacto,
                            @Param("usuario") String usuario);
    @Query(value = "select * from contacto c where c.correocontacto =:correocontacto", nativeQuery = true)
    Contacto findByMail(@Param("correocontacto") String correoContacto);

}
