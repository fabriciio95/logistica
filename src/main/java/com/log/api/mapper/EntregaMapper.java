package com.log.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.log.api.model.input.EntregaDTOInput;
import com.log.api.model.output.EntregaDTOOutput;
import com.log.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaMapper {

	private ModelMapper modelMapper;
	
	public EntregaDTOOutput toDTO(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTOOutput.class);
	}
	
	public List<EntregaDTOOutput> toListDTO(List<Entrega> entregas) {
		return entregas.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaDTOInput entregaDTOInput) {
		return modelMapper.map(entregaDTOInput, Entrega.class);
	}
}
