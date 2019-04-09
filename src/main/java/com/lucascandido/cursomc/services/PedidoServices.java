package com.lucascandido.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascandido.cursomc.domain.Pedido;
import com.lucascandido.cursomc.repositories.PedidoRepository;
import com.lucascandido.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoServices {

	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		
			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado !\nID: "+id+"Tipo: "+Pedido.class.getName()));
	}
}