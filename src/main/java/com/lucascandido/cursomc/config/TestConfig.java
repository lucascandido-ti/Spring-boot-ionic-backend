package com.lucascandido.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucascandido.cursomc.services.DBServices;
import com.lucascandido.cursomc.services.EmailServices;
import com.lucascandido.cursomc.services.MockEmailServices;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBServices dbServices;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		dbServices.instantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailServices emailServices() {
		return new MockEmailServices();
	}
}
