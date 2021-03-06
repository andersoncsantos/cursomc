package com.anderson.cursomc.resources;

import com.anderson.cursomc.domain.Produto;
import com.anderson.cursomc.domain.dto.ProdutoDTO;
import com.anderson.cursomc.resources.utils.URL;
import com.anderson.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> findResource(@PathVariable Integer id) {
		Produto produto = produtoService.findProduto(id);
		return ResponseEntity.ok().body(produto);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPageResource(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "lines", defaultValue = "24") Integer lines,
			@RequestParam(value = "orderby", defaultValue = "nome") String orderby,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoService.searchProduto(nomeDecoded, ids, page, lines, orderby, direction);
		/* Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj)); with lambda*/
		Page<ProdutoDTO> listDTO = list.map(ProdutoDTO::new);
		return ResponseEntity.ok().body(listDTO);
	}
}
