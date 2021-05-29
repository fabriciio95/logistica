package com.log.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.log.domain.model.Entrega;
import com.log.domain.model.Motorista;
import com.log.domain.model.StatusEntrega;
import com.log.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StatusEntregaService {

	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;
	private RegistroOcorrenciasService registroOcorrenciasService;
	private BuscaMotoristaService buscaMotoristaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		entrega.finalizarOuCancelar(StatusEntrega.FINALIZADA);
		entregaRepository.save(entrega);
	}
	
	@Transactional
	public void cancelar(Long entregaId, String descricaoOcorrencia) {
		registroOcorrenciasService.registrar(entregaId, descricaoOcorrencia);
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		entrega.finalizarOuCancelar(StatusEntrega.CANCELADA);
		entregaRepository.save(entrega);
	}
	
	@Transactional
	public void colocarEmAndamento(Long entregaId, Long motoristaId) { 
		Motorista motorista = buscaMotoristaService.buscar(motoristaId);
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		entrega.setMotorista(motorista);
		entrega.colocarStatusEmAndamento();
		entregaRepository.save(entrega);
	}
}
