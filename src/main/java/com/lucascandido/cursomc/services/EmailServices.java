package com.lucascandido.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.lucascandido.cursomc.domain.Pedido;

public interface EmailServices {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
