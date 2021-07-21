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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.log.api.mapper.ClienteMapper;
import com.log.api.model.input.ClienteDTOInput;
import com.log.api.model.output.ClienteDTOOutput;
import com.log.domain.model.Cliente;
import com.log.domain.repository.ClienteRepository;
import com.log.domain.service.CrudClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private CrudClienteService crudClienteService;
	private ClienteMapper clienteMapper;
	

	@GetMapping(produces="application/json")
	public Page<ClienteDTOOutput> listar(Pageable pageable) {
		return clienteMapper.toPageDTO(crudClienteService.listar(pageable));
	}
	
	
	@GetMapping(value = "/{clienteId}", produces="application/json")
	public ResponseEntity<ClienteDTOOutput> buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(clienteMapper.toDTO(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(value = "/findbycpf", produces="application/json")
	public Page<ClienteDTOOutput> buscarPorCpf(@RequestParam(required = true, name = "cpf") String cpf, Pageable pageable) {
		return clienteMapper.toPageDTO(clienteRepository.findByCpfContaining(cpf, pageable));
	}
	
	@PostMapping(consumes = "application/json", produces="application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClienteDTOOutput adicionar(@RequestBody @Valid ClienteDTOInput clienteDTOinput) {
		Cliente cliente = clienteMapper.toEntity(clienteDTOinput);
		cliente = crudClienteService.salvar(cliente);
		return clienteMapper.toDTO(cliente);
	}
	
	@PutMapping(value = "/{clienteId}", consumes = "application/json", produces="application/json")
	public ResponseEntity<ClienteDTOOutput> atualizar(@PathVariable Long clienteId,
			@Valid @RequestBody ClienteDTOInput clienteDTOInput) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliente = clienteMapper.toEntity(clienteDTOInput);
		cliente.setId(clienteId);
		cliente = crudClienteService.salvar(cliente);
		return ResponseEntity.ok(clienteMapper.toDTO(cliente));
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		crudClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}
