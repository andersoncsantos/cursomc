package com.anderson.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.cursomc.domain.Cidade;
import com.anderson.cursomc.domain.Cliente;
import com.anderson.cursomc.domain.Endereco;
import com.anderson.cursomc.domain.dto.ClienteDTO;
import com.anderson.cursomc.domain.dto.NewClienteDTO;
import com.anderson.cursomc.domain.enums.TipoCliente;
import com.anderson.cursomc.repositories.ClienteRepository;
import com.anderson.cursomc.repositories.EnderecoRepository;
import com.anderson.cursomc.services.exceptions.DataIntegrityException;
import com.anderson.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}


	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui entidades relacionadas");
		}

	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer lines, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, lines, Direction.valueOf(direction), orderBy );
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}

	public Cliente fromDTO(NewClienteDTO newClienteDTO) {

		Cliente cliente = new Cliente(null, newClienteDTO.getNome(), newClienteDTO.getEmail(),
				newClienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(newClienteDTO.getTipoCliente()),
                bCryptPasswordEncoder.encode(newClienteDTO.getSenha()));

		Cidade cidade = new Cidade(newClienteDTO.getCidadeId(), null, null);

		Endereco endereco = new Endereco(null, newClienteDTO.getLogradouro(), newClienteDTO.getNumero(),
				newClienteDTO.getComplemento(), newClienteDTO.getBairro(), newClienteDTO.getCep(), cliente, cidade);

		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(newClienteDTO.getTelefone1());

		if (newClienteDTO.getTelefone2() != null) {
			cliente.getTelefones().add(newClienteDTO.getTelefone2());
		}

		if (newClienteDTO.getTelefone3() != null) {
			cliente.getTelefones().add(newClienteDTO.getTelefone3());
		}

		return cliente;
	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}
}
