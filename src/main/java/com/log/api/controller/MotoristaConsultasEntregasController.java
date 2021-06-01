package com.log.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.api.mapper.EntregaMapper;
import com.log.api.mapper.MotoristaMapper;
import com.log.api.model.output.EntregaDTOOutput;
import com.log.api.model.output.MotoristaEntregaDTOOutput;
import com.log.domain.model.Motorista;
import com.log.domain.service.BuscaEntregaService;
import com.log.domain.service.CrudMotoristaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/motoristas/{idMotorista}/entregas")
public class MotoristaConsultasEntregasController {

	private BuscaEntregaService buscaEntregaService;
	private CrudMotoristaService crudMotoristaService;
	private EntregaMapper entregaMapper;
	private MotoristaMapper motoristaMapper;
	
	@GetMapping
	public ResponseEntity<MotoristaEntregaDTOOutput> obterTodasEntregas(
			@PathVariable(name = "idMotorista", required = true) Long idMotorista, Pageable pageable) {
		Motorista motorista = crudMotoristaService.buscarPorId(idMotorista);
		Page<EntregaDTOOutput> entregas = buscaEntregaService.obterEntregasMotorista(idMotorista, pageable)
				.map(entrega -> entregaMapper.toDTO(entrega));
		MotoristaEntregaDTOOutput motoristaDTO = motoristaMapper.toMotoristaEntregaDTO(entregas, motorista);
		return ResponseEntity.ok(motoristaDTO);
		
	}
}
