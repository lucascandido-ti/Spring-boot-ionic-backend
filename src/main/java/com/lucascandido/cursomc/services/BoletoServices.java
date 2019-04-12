package com.lucascandido.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.lucascandido.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoServices {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoBoleto) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoBoleto);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
