package com.lucascandido.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascandido.cursomc.domain.Cidade;
import com.lucascandido.cursomc.repositories.CidadeRepository;

@Service
public class CidadeServices {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	public List<Cidade> findByEstado(Integer estadoId){
		return cidadeRepository.findCidades(estadoId);
	}
}
