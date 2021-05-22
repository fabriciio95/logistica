package com.log.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.log.domain.exception.NegocioException;
import com.log.domain.model.Cliente;
import com.log.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudClienteService {

	private ClienteRepository clienteRepository;
	
	
	public List<Cliente> listar(){
		return clienteRepository.findAll();
	}
	
	public Cliente buscar(Long clienteId) throws NegocioException {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}
	
	public Cliente buscarPorId(Long clienteId) {
		return clienteRepository.findById(clienteId).orElse(null);
	}
	
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
			.stream()
			.anyMatch(clienteExiste -> !clienteExiste.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long idCliente) {
		clienteRepository.deleteById(idCliente);
	}
}
