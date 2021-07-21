package com.log.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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
	
	public Page<EntregaDTOOutput> toListDTO(Page<Entrega> entregas) {
		return entregas.map(this::toDTO);
	}
	
	public Entrega toEntity(EntregaDTOInput entregaDTOInput) {
		return modelMapper.map(entregaDTOInput, Entrega.class);
	}
}
