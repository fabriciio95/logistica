package com.log.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	

	@GetMapping
	public List<ClienteDTOOutput> listar() {
		return clienteMapper.toListDTO(crudClienteService.listar());
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteDTOOutput> buscar(@PathVariable Long clienteId) {
		Cliente cliente = crudClienteService.buscarPorId(clienteId);
		return cliente != null ? ResponseEntity.ok(clienteMapper.toDTO(cliente)) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClienteDTOOutput adicionar(@RequestBody @Valid ClienteDTOInput clienteDTOinput) {
		Cliente cliente = clienteMapper.toEntity(clienteDTOinput);
		cliente = crudClienteService.salvar(cliente);
		return clienteMapper.toDTO(cliente);
	}
	
	@PutMapping("/{clienteId}")
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
