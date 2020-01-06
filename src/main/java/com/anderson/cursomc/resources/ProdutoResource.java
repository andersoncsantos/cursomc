package com.anderson.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anderson.cursomc.domain.Produto;
import com.anderson.cursomc.domain.dto.ProdutoDTO;
import com.anderson.cursomc.resources.utils.URL;
import com.anderson.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto = produtoService.find(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "0") String categorias, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "lines", defaultValue = "24") Integer lines, 
			@RequestParam(value = "orderby", defaultValue = "nome") String orderby, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> categoria = produtoService.search(nomeDecoded, ids, page, lines, orderby, direction);
		Page<ProdutoDTO> produtoDTO = categoria.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(produtoDTO);
	}

}
