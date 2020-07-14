package com.anderson.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public ResponseEntity<Categoria> findResource(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findCategoria(id);
		return ResponseEntity.ok().body(categoria);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertResource(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria = categoriaService.insertCategoria(categoria);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateResource(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoriaService.updateCategoria(categoria);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteResource(@PathVariable Integer id) {
		categoriaService.deleteCategoria(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAllResource() {
		List<Categoria> categoria = categoriaService.findAllCategoria();
		/*List<CategoriaDTO> categoriaDTO = categoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); with lambda*/
		List<CategoriaDTO> categoriaDTO = categoria.stream().map(CategoriaDTO::new).collect(Collectors.toList()); // with method reference
		return ResponseEntity.ok().body(categoriaDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPageResource(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "lines", defaultValue = "24") Integer lines, 
			@RequestParam(value = "orderby", defaultValue = "nome") String orderby, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> categoria = categoriaService.findPageCategoria(page, lines, orderby, direction);
		Page<CategoriaDTO> categoriaDTO = categoria.map(CategoriaDTO::new); // with method reference
		return ResponseEntity.ok().body(categoriaDTO);
	}

}
