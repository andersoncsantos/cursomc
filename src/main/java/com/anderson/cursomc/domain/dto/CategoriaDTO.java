package com.anderson.cursomc.domain.dto;

import java.io.Serializable;

import com.anderson.cursomc.domain.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}

}
