package com.log.domain.service;

import org.springframework.stereotype.Service;

import com.log.domain.exception.EntidadeNaoEncontradaException;
import com.log.domain.model.Motorista;
import com.log.domain.repository.MotoristaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaMotoristaService {
	
	private MotoristaRepository motoristaRepository;

	public Motorista buscar(Long idMotorista) {
		return motoristaRepository.findById(idMotorista).
				orElseThrow(() -> new EntidadeNaoEncontradaException("Motorista não encontrado"));
	}
}
