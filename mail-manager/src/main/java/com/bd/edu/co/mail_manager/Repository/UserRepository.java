package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Usuario, String> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO usuario (usuario, idestado, idpais, nombre, apellido, fechanacimiento, fechacreacion, " +
            "correoalterno, celular, password) " +
            "VALUES (:username, :idestado, :idpais, :nombre, :apellido, :fechanacimiento, :fechacreacion, :correoalterno, :celular, :password)", nativeQuery = true)
    void insertUser(@Param("username") String username,
                    @Param("idestado") String idEstado,
                    @Param("idpais") String idPais,
                    @Param("nombre") String nombre,
                    @Param("apellido") String apellido,
                    @Param("fechanacimiento") Date fechaNacimiento,
                    @Param("fechacreacion") Date fechaCreacion,
                    @Param("correoalterno") String correoAlterno,
                    @Param("celular") String celular,
                    @Param("password") String password);

    @Query(value = "SELECT * FROM USUARIO U WHERE U.USUARIO=:username", nativeQuery = true)
    Usuario getUserByIdNative(@Param("username") String id);

    @Query(value = "SELECT U.IDPAIS FROM USUARIO U WHERE U.USUARIO = :usuario", nativeQuery = true)
    String getPaisIdByUsername(@Param("usuario") String username);

    @Query(value = "SELECT * FROM USUARIO", nativeQuery = true)
    List<Usuario> getAllUsers();
}
