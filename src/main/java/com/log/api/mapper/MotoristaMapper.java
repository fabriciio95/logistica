package com.log.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.log.api.model.input.MotoristaDTOInput;
import com.log.api.model.output.MotoristaDTOOutput;
import com.log.domain.model.Motorista;

@Component
public class MotoristaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public MotoristaDTOOutput toDTO(Motorista motorista) {
		return modelMapper.map(motorista, MotoristaDTOOutput.class);
	}
	
	public List<MotoristaDTOOutput> toListDTO(List<Motorista> motoristas) {
		return motoristas.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public Motorista toEntity(MotoristaDTOInput motoristaDTOInput) {
		return modelMapper.map(motoristaDTOInput, Motorista.class);
	}
}
