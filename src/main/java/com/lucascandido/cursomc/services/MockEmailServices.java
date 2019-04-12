package com.lucascandido.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailServices extends AbstractEmailServices{

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailServices.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de Email");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

}