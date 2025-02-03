package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

    @Query(value = "SELECT * FROM CATEGORIA C WHERE C.IDCATEGORIA = :idcategoria", nativeQuery = true)
    Categoria getCategoriaById(@Param("idcategoria") String idCategoria);
}
