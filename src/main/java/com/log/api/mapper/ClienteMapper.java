package com.log.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.log.api.model.input.PersonagemDTOInput;
import com.log.api.model.output.ClienteDTOOutput;
import com.log.api.model.output.ClienteEntregaDTOOutput;
import com.log.api.model.output.EntregaDTOOutput;
import com.log.api.model.output.PersonagemResumoDTOOutput;
import com.log.domain.model.Cliente;

@Component
public class ClienteMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteDTOOutput toDTO(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDTOOutput.class);
	}
	
	public Page<ClienteDTOOutput> toPageDTO(Page<Cliente> clientes) {
		return clientes.map(this::toDTO);
	}
	
	public Cliente toEntity(PersonagemDTOInput clienteDTOInput) {
		return modelMapper.map(clienteDTOInput, Cliente.class);
	}
	
	public ClienteEntregaDTOOutput toClienteEntregaDTO(Page<EntregaDTOOutput> entregas, Cliente cliente) {
		PersonagemResumoDTOOutput clienteResumoDTO = modelMapper.map(cliente, PersonagemResumoDTOOutput.class);
		entregas.forEach(entrega -> entrega.setCliente(null));
		ClienteEntregaDTOOutput clienteEntregaDTO = new ClienteEntregaDTOOutput();
		clienteEntregaDTO.setCliente(clienteResumoDTO);
		clienteEntregaDTO.setEntregas(entregas);
		return clienteEntregaDTO;
	}
}
