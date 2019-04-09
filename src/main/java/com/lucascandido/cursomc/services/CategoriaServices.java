package com.lucascandido.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascandido.cursomc.domain.Categoria;
import com.lucascandido.cursomc.repositories.CategoriaRepository;
import com.lucascandido.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaServices {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		
			Optional<Categoria> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado !\nID: "+id+"Tipo: "+Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
}