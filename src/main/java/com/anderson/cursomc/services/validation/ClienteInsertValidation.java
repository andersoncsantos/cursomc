package com.anderson.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.anderson.cursomc.domain.Cliente;
import com.anderson.cursomc.domain.dto.NewClienteDTO;
import com.anderson.cursomc.domain.enums.TipoCliente;
import com.anderson.cursomc.repositories.ClienteRepository;
import com.anderson.cursomc.resources.exception.FieldMessage;
import com.anderson.cursomc.services.validation.utils.BR;

public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert, NewClienteDTO> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public boolean isValid(NewClienteDTO newClienteDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (newClienteDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(newClienteDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if (newClienteDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod())
				&& !BR.isValidCNPJ(newClienteDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente cliente = clienteRepository.findByEmail(newClienteDTO.getEmail());
		
		if(cliente != null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
