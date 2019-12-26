package com.anderson.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderson.cursomc.domain.Categoria;
import com.anderson.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	public Categoria buscar(Integer id) {
		Categoria obj = categoriaRepository.findById(id).get();
		return obj;
	}
	
}
