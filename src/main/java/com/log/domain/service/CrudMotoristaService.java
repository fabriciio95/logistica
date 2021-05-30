package com.log.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.log.domain.exception.EntidadeNaoEncontradaException;
import com.log.domain.exception.NegocioException;
import com.log.domain.model.Motorista;
import com.log.domain.repository.MotoristaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CrudMotoristaService {

	private MotoristaRepository motoristaRepository;
	
	
	public List<Motorista> listar() {
		return motoristaRepository.findAll();
	}
	
	public Motorista buscarPorId(Long id) {
		return motoristaRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Motorista não encontrado"));
	}
	
	@Transactional
	public void excluir(Long id) {
		motoristaRepository.deleteById(id);
	}
	
	@Transactional
	public Motorista salvar(Motorista motorista) {
		
		boolean emailEmUso = motoristaRepository.findByEmail(motorista.getEmail())
				.stream()
				.anyMatch(motoristaExistente -> !motoristaExistente.equals(motorista));
		
		boolean cpfEmUso = motoristaRepository.findByCpf(motorista.getCpf())
				.stream()
				.anyMatch(motoristaExistente -> !motoristaExistente.equals(motorista));
		
		if(emailEmUso || cpfEmUso) {
			String msg = emailEmUso ? "email" : "cpf";
			throw new NegocioException(String.format("Já existe um motorista cadastrado com este %s.", msg));
		}
		
		return motoristaRepository.save(motorista);
	}
}
