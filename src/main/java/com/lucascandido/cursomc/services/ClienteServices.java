package com.lucascandido.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascandido.cursomc.domain.Cliente;
import com.lucascandido.cursomc.repositories.ClienteRepository;
import com.lucascandido.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteServices {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) {
		
			Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado !\nID: "+id+"Tipo: "+Cliente.class.getName()));
	}
}