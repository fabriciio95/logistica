package com.log.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.log.domain.model.Entrega;
import com.log.domain.model.StatusEntrega;
import com.log.domain.repository.EntregaRepository;
import com.log.domain.repository.ObjetoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	private CrudClienteService crudClienteService;
	private ObjetoRepository objetoRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		entrega.setCliente(crudClienteService.buscarPorId(entrega.getCliente().getId()));
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		entrega = entregaRepository.save(entrega);
		salvarObjetos(entrega);
		return entrega;
	}
	
	@Transactional
	public void salvarObjetos(Entrega entrega) {
		entrega.getObjetos().forEach(obj -> {
			obj.setEntrega(entrega);
			objetoRepository.save(obj);
		});
	}
}
