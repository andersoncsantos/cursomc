package com.anderson.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
	private ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NewClienteDTO newClienteDTO) {
		Cliente cliente = clienteService.fromDTO(newClienteDTO);
		cliente = clienteService.insert(cliente);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Integer id) {
		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> cliente = clienteService.findAll();
		List<ClienteDTO> clienteDTO = cliente.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clienteDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "lines", defaultValue = "24") Integer lines, 
			@RequestParam(value = "orderby", defaultValue = "nome") String orderby, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> cliente = clienteService.findPage(page, lines, orderby, direction);
		Page<ClienteDTO> clienteDTO = cliente.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(clienteDTO);
	}

}
