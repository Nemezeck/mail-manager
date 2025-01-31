package com.bd.edu.co.mail_manager.Repository;

import com.bd.edu.co.mail_manager.Entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

}
