package com.anderson.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.anderson.cursomc.domain.Categoria;
import com.anderson.cursomc.domain.dto.CategoriaDTO;
import com.anderson.cursomc.repositories.CategoriaRepository;
import com.anderson.cursomc.services.exceptions.DataIntegrityException;
import com.anderson.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findCategoria(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insertCategoria(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
	
	public void updateCategoria(Categoria categoria) {
		Categoria newCategoria = findCategoria(categoria.getId());
		updateData(newCategoria, categoria);
		categoriaRepository.save(newCategoria);
	}

	public void deleteCategoria(Integer id) {
		findCategoria(id);
		try {
			categoriaRepository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que já possui produtos");
		}
		
	}

	public List<Categoria> findAllCategoria() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPageCategoria(Integer page, Integer lines, String orderby, String direction) {
		PageRequest pageRequest = PageRequest.of(page, lines, Direction.valueOf(direction), orderby );
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());

	}
	
	private void updateData(Categoria newCategoria, Categoria categoria) {
		newCategoria.setNome(categoria.getNome());
	}

}
