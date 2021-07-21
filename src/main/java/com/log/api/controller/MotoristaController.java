package com.log.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.log.api.mapper.MotoristaMapper;
import com.log.api.model.input.MotoristaDTOInput;
import com.log.api.model.output.MotoristaDTOOutput;
import com.log.domain.model.Motorista;
import com.log.domain.repository.MotoristaRepository;
import com.log.domain.service.CrudMotoristaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/motoristas")
@AllArgsConstructor
public class MotoristaController {

	private MotoristaRepository motoristaRepository;
	private CrudMotoristaService crudMotoristaService;
	private MotoristaMapper motoristaMapper;
	
	@PostMapping(consumes = "application/json", produces="application/json")
	public ResponseEntity<MotoristaDTOOutput> criar(@RequestBody @Valid MotoristaDTOInput motoristaDTOInput) {
		Motorista motorista = motoristaMapper.toEntity(motoristaDTOInput);
		motorista = crudMotoristaService.salvar(motorista);
		return ResponseEntity.status(HttpStatus.CREATED).body(motoristaMapper.toDTO(motorista));
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json", produces="application/json")
	public ResponseEntity<MotoristaDTOOutput> atualizar(@PathVariable Long id,
			@RequestBody @Valid MotoristaDTOInput motoristaDTOInput) {
		
		if(!motoristaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		Motorista motorista = motoristaMapper.toEntity(motoristaDTOInput);
		motorista.setId(id);
		motorista = motoristaRepository.save(motorista);
		return ResponseEntity.ok(motoristaMapper.toDTO(motorista));
	}
	
	@GetMapping(produces="application/json")
	public Page<MotoristaDTOOutput> listar(Pageable pageable) {
		return motoristaMapper.toListDTO(crudMotoristaService.listar(pageable));
	}
	
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<MotoristaDTOOutput> obterPorId(@PathVariable Long id) {
		return motoristaRepository.findById(id)
				.map(motorista -> ResponseEntity.ok(motoristaMapper.toDTO(motorista)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/findbycpf", produces="application/json")
	public Page<MotoristaDTOOutput> listarPorCpf(@RequestParam(required = true, name = "cpf") String cpf, Pageable pageable) {
		return motoristaMapper.toListDTO(motoristaRepository.findByCpfContaining(cpf, pageable));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if(!motoristaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		motoristaRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
