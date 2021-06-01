package com.log.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.log.api.mapper.EntregaMapper;
import com.log.api.model.output.EntregaDTOOutput;
import com.log.domain.model.StatusEntrega;
import com.log.domain.service.BuscaEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/busca")
public class EntregaConsultasController {

	private BuscaEntregaService buscaEntregaService;
	private EntregaMapper entregaMapper;
	
	@GetMapping("/status")
	public ResponseEntity<Page<EntregaDTOOutput>> buscarPorStatus(@RequestParam(name = "status",
		required = true) String statusEmString, Pageable pageable) {
		StatusEntrega statusEntrega = StatusEntrega.valueOf(statusEmString.toUpperCase());
		Page<EntregaDTOOutput> entregas =  buscaEntregaService.obterEntregasPorStatus(statusEntrega, pageable)
					.map(entrega -> entregaMapper.toDTO(entrega));
		return ResponseEntity.ok(entregas);
	}
	
}
