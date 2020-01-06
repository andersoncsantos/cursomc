package com.anderson.cursomc.domain.dto;

import java.io.Serializable;

import com.anderson.cursomc.domain.Produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double preco;
	
	public ProdutoDTO(Produto produto) {
		id = produto.getId();
		nome = produto.getNome();
		preco = produto.getPreco();
	}
}
