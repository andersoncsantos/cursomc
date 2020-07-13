package com.anderson.cursomc.services;

import com.anderson.cursomc.domain.Categoria;
import com.anderson.cursomc.domain.Produto;
import com.anderson.cursomc.repositories.CategoriaRepository;
import com.anderson.cursomc.repositories.ProdutoRepository;
import com.anderson.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto findProduto(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> searchProduto(String nome, List<Integer> ids, Integer page, Integer lines, String orderby, String direction) {
		PageRequest pageRequest = PageRequest.of(page, lines, Sort.Direction.valueOf(direction), orderby);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
	}

}
