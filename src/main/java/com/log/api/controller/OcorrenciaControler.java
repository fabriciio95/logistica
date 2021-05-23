package com.log.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.log.api.mapper.OcorrenciaMapper;
import com.log.api.model.input.OcorrenciaDTOInput;
import com.log.api.model.output.OcorrenciaDTOOutput;
import com.log.domain.model.Entrega;
import com.log.domain.model.Ocorrencia;
import com.log.domain.service.BuscaEntregaService;
import com.log.domain.service.RegistroOcorrenciasService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaControler {
	
	private RegistroOcorrenciasService registroOcorrenciaService;
	private OcorrenciaMapper ocorrenciaMapper;
	private BuscaEntregaService buscaEntregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTOOutput registrar(@PathVariable Long entregaId, @RequestBody @Valid OcorrenciaDTOInput ocorrenciaDTOInput) {
		Ocorrencia ocorrencia = registroOcorrenciaService.registrar(entregaId, ocorrenciaDTOInput.getDescricao());
		return ocorrenciaMapper.toDTO(ocorrencia);
	}
	
	@GetMapping
	public List<OcorrenciaDTOOutput> listar(@PathVariable Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		return ocorrenciaMapper.toListDTO(entrega.getOcorrencias());
	}
}
