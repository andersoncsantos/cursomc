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

import com.anderson.cursomc.domain.Cliente;
import com.anderson.cursomc.domain.dto.ClienteDTO;
import com.anderson.cursomc.domain.dto.NewClienteDTO;
import com.anderson.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findResource(@PathVariable Integer id) {
		Cliente cliente = clienteService.findCliente(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertResource(@Valid @RequestBody NewClienteDTO newClienteDTO) {
		Cliente cliente = clienteService.fromDTO(newClienteDTO);
		cliente = clienteService.insertCliente(cliente);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateResource(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente.setId(id);
		clienteService.updateCliente(cliente);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteResource(@PathVariable Integer id) {
		clienteService.deleteCliente(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAllResource() {
		List<Cliente> cliente = clienteService.findAllCliente();
		List<ClienteDTO> clienteDTO = cliente.stream().map(ClienteDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(clienteDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPageResource(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "lines", defaultValue = "24") Integer lines, 
			@RequestParam(value = "orderby", defaultValue = "nome") String orderby, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> cliente = clienteService.findPageCliente(page, lines, orderby, direction);
		Page<ClienteDTO> clienteDTO = cliente.map(ClienteDTO::new);
		return ResponseEntity.ok().body(clienteDTO);
	}

}
