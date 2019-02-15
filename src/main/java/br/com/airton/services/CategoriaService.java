package br.com.airton.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.airton.domain.Categoria;
import br.com.airton.repository.CategoriaRepository;
import br.com.airton.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException
				("Objeto não encontrado! Id: " + id 
						+ ", Tipo: " + Categoria.class.getName()));
	}
	
}
