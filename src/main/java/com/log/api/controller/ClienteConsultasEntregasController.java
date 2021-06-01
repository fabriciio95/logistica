package com.log.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.log.api.mapper.ClienteMapper;
import com.log.api.mapper.EntregaMapper;
import com.log.api.model.output.ClienteEntregaDTOOutput;
import com.log.api.model.output.EntregaDTOOutput;
import com.log.domain.model.Cliente;
import com.log.domain.service.BuscaEntregaService;
import com.log.domain.service.CrudClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes/{idCliente}/entregas")
public class ClienteConsultasEntregasController {
	
	private BuscaEntregaService buscaEntregaService;
	private CrudClienteService crudClienteService;
	private EntregaMapper entregaMapper;
	private ClienteMapper clienteMapper;

	@GetMapping
	public ResponseEntity<ClienteEntregaDTOOutput> obterTodasEntregas(@PathVariable(name = "idCliente", required = true) Long idCliente,
			Pageable pageable) {
		Cliente cliente = crudClienteService.buscarPorId(idCliente);
		Page<EntregaDTOOutput> entregas = buscaEntregaService.obterEntregasCliente(idCliente, pageable)
				.map(entrega -> entregaMapper.toDTO(entrega));
		ClienteEntregaDTOOutput clienteEntregaDTO = clienteMapper.toClienteEntregaDTO(entregas, cliente);
		return ResponseEntity.ok(clienteEntregaDTO);
	}
}
