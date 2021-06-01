package com.log.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.log.domain.exception.EntidadeNaoEncontradaException;
import com.log.domain.model.Entrega;
import com.log.domain.model.StatusEntrega;
import com.log.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId).orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
	
	public Page<Entrega> obterEntregasCliente(Long clienteId, Pageable pageable) {
		return entregaRepository.obterEntregasCliente(clienteId, pageable);
	}
	
	public Page<Entrega> obterEntregasMotorista(Long motoristaId, Pageable pageable) {
		return entregaRepository.obterEntregasMotorista(motoristaId, pageable);
	}
	
	public Page<Entrega> obterEntregasPorStatus(StatusEntrega statusEntrega, Pageable pageable) {
		return entregaRepository.obterEntregasPorStatus(statusEntrega, pageable);
	}
}
