package com.anderson.cursomc.domain.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewClienteDTO implements Serializable {
	
	//https://github.com/acenelio/springboot2-ionic-backend/commit/2592dd866080f619f12f51266f39dbdc59e1fb83
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipoCliente;
	
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;

}
