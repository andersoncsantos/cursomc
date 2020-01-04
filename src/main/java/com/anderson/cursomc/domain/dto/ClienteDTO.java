package com.anderson.cursomc.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.anderson.cursomc.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import com.anderson.cursomc.domain.Cliente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ClienteUpdate
public class ClienteDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 120, message = "O tamanho deve ter entre 5 e 120 caracteres" )
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "Email inválido!")
	private String email;
	
	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}	

}
