package com.log.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.log.api.model.input.ClienteDTOInput;
import com.log.api.model.output.ClienteDTOOutput;
import com.log.domain.model.Cliente;

@Component
public class ClienteMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteDTOOutput toDTO(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDTOOutput.class);
	}
	
	public List<ClienteDTOOutput> toListDTO(List<Cliente> clientes) {
		return clientes.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public Cliente toEntity(ClienteDTOInput clienteDTOInput) {
		return modelMapper.map(clienteDTOInput, Cliente.class);
	}
}
