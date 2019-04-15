package com.lucascandido.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucascandido.cursomc.services.DBServices;
import com.lucascandido.cursomc.services.EmailServices;
import com.lucascandido.cursomc.services.SmtpEmailServices;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBServices dbServices;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbServices.instantiateTestDatabase();
		return true;
		
	}
	
	@Bean
	public EmailServices emailService() {
		return new SmtpEmailServices();
	}
}
