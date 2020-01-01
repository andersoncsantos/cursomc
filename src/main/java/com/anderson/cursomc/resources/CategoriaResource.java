package com.anderson.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anderson.cursomc.domain.Categoria;
import com.anderson.cursomc.domain.dto.CategoriaDTO;
import com.anderson.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria categoria = categoriaService.find(id);
		return ResponseEntity.ok().body(categoria);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categoria = categoriaService.findAll();
		List<CategoriaDTO> categoriaDTO = categoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriaDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "lines", defaultValue = "24") Integer lines, 
			@RequestParam(value = "orderby", defaultValue = "nome") String orderby, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> categoria = categoriaService.findPage(page, lines, orderby, direction);
		Page<CategoriaDTO> categoriaDTO = categoria.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(categoriaDTO);
	}

}
