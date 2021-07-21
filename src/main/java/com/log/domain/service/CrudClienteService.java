package com.log.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.log.domain.exception.EntidadeNaoEncontradaException;
import com.log.domain.exception.NegocioException;
import com.log.domain.model.Cliente;
import com.log.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudClienteService {

	private ClienteRepository clienteRepository;
	
	
	public Page<Cliente> listar(Pageable pageable){
		return clienteRepository.findAll(pageable);
	}
	
	public Cliente buscarPorId(Long clienteId) throws NegocioException {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
	}
	
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
			.stream()
			.anyMatch(clienteExiste -> !clienteExiste.equals(cliente));
		
		boolean cpfEmUso = clienteRepository.findByCpf(cliente.getCpf())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso || cpfEmUso) {
			String msg = emailEmUso ? "e-mail" : "cpf";
			throw new NegocioException(String.format("Já existe um cliente cadastrado com este %s.", msg));
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long idCliente) {
		clienteRepository.deleteById(idCliente);
	}
}
