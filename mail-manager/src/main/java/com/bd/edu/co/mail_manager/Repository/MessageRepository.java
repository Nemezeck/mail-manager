package com.bd.edu.co.mail_manager.Repository;


import com.bd.edu.co.mail_manager.Entity.Mensaje;
import com.bd.edu.co.mail_manager.Entity.MensajePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Mensaje, MensajePK> {
}
