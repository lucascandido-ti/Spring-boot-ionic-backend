package com.lucascandido.cursomc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucascandido.cursomc.services.S3Services;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private S3Services s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		s3Service.uploadFile("C:\\Users\\Lucas Candido\\Documents\\STS\\projects\\fotos\\firstblackrole.png");
		
	}

}
