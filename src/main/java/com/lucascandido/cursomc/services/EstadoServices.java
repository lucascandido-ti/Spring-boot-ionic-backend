package com.lucascandido.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascandido.cursomc.domain.Estado;
import com.lucascandido.cursomc.repositories.EstadoRepository;

@Service
public class EstadoServices {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> findAll(){
		return estadoRepository.findAllByOrderByNome();
	}
}
