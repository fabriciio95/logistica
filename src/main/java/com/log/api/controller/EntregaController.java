package com.log.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.log.api.mapper.EntregaMapper;
import com.log.api.model.input.EntregaDTOInput;
import com.log.api.model.input.MotoristaDTOIdInput;
import com.log.api.model.input.OcorrenciaDTOInput;
import com.log.api.model.output.EntregaDTOOutput;
import com.log.domain.model.Entrega;
import com.log.domain.repository.EntregaRepository;
import com.log.domain.service.SolicitacaoEntregaService;
import com.log.domain.service.StatusEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private EntregaMapper entregaMapper;
	private StatusEntregaService statusEntregaService;
	
	
	@PostMapping(consumes = "application/json", produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTOOutput solicitar(@RequestBody @Valid EntregaDTOInput entregaDTOInput) {
		Entrega entrega = entregaMapper.toEntity(entregaDTOInput);
		entrega = solicitacaoEntregaService.solicitar(entrega);
		return entregaMapper.toDTO(entrega);
	}
	
	@GetMapping(produces="application/json")
	public Page<EntregaDTOOutput> listar(Pageable pageable){
		return entregaMapper.toListDTO(entregaRepository.findAll(pageable));
	}
	
	@GetMapping(value = "/{entregaId}", produces="application/json")
	public ResponseEntity<EntregaDTOOutput> buscarPorId(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaMapper.toDTO(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value = "/{entregaId}/finalizacao", produces="application/json")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		statusEntregaService.finalizar(entregaId);
	}
	
	@PutMapping(value = "/{entregaId}/cancelamento", produces="application/json") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long entregaId, @RequestBody @Valid OcorrenciaDTOInput ocorrenciaDTOInput) {
		statusEntregaService.cancelar(entregaId, ocorrenciaDTOInput.getDescricao());
	}
	
	@PutMapping(value = "/{entregaId}/andamento", produces="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void andamento(@PathVariable Long entregaId, @RequestBody @Valid MotoristaDTOIdInput motoristaDTOIdInput) {
		statusEntregaService.colocarEmAndamento(entregaId, motoristaDTOIdInput.getMotorista().getId());
	}
}
