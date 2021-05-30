package com.log.api.controller;

import java.util.List;

import javax.validation.Valid;

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
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTOOutput solicitar(@RequestBody @Valid EntregaDTOInput entregaDTOInput) {
		Entrega entrega = entregaMapper.toEntity(entregaDTOInput);
		entrega = solicitacaoEntregaService.solicitar(entrega);
		return entregaMapper.toDTO(entrega);
	}
	
	@GetMapping
	public List<EntregaDTOOutput> listar(){
		return entregaMapper.toListDTO(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTOOutput> buscarPorId(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(entregaMapper.toDTO(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		statusEntregaService.finalizar(entregaId);
	}
	
	@PutMapping("/{entregaId}/cancelamento") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long entregaId, @RequestBody @Valid OcorrenciaDTOInput ocorrenciaDTOInput) {
		statusEntregaService.cancelar(entregaId, ocorrenciaDTOInput.getDescricao());
	}
	
	@PutMapping("/{entregaId}/andamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void andamento(@PathVariable Long entregaId, @RequestBody @Valid MotoristaDTOIdInput motoristaDTOIdInput) {
		statusEntregaService.colocarEmAndamento(entregaId, motoristaDTOIdInput.getMotorista().getId());
	}
}
