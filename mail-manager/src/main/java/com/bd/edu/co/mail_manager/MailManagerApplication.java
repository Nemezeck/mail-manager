package com.bd.edu.co.mail_manager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.bd.edu.co.mail_manager.Config",
		"com.bd.edu.co.mail_manager.Controller",
		"com.bd.edu.co.mail_manager.Service"
})
@EntityScan(basePackages = "com.bd.edu.co.mail_manager.Entity")
@EnableJpaRepositories(basePackages = "com.bd.edu.co.mail_manager.Repository")
public class MailManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailManagerApplication.class, args);
	}
}

