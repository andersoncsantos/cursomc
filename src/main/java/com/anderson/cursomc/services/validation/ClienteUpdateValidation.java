package com.anderson.cursomc.services.validation;

import com.anderson.cursomc.domain.Cliente;
import com.anderson.cursomc.domain.dto.ClienteDTO;
import com.anderson.cursomc.domain.dto.NewClienteDTO;
import com.anderson.cursomc.domain.enums.TipoCliente;
import com.anderson.cursomc.repositories.ClienteRepository;
import com.anderson.cursomc.resources.exception.FieldMessage;
import com.anderson.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidation implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute ( HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE );
		Integer urlId = Integer.parseInt ( map.get ( "id" ) );

        List<FieldMessage> list = new ArrayList<> ();

        Cliente cliente = clienteRepository.findByEmail ( clienteDTO.getEmail () );

        if (cliente != null && !cliente.getId ().equals ( urlId )) {
            list.add ( new FieldMessage ( "email", "Email já existe" ) );
        }

        for (FieldMessage fieldMessage : list) {
            context.disableDefaultConstraintViolation ();
            context.buildConstraintViolationWithTemplate ( fieldMessage.getMessage () )
                    .addPropertyNode ( fieldMessage.getFieldName () ).addConstraintViolation ();
        }

        return list.isEmpty ();
    }

}
