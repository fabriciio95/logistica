package com.log.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.log.api.model.output.DestinatarioDTOOutput;
import com.log.domain.model.Destinatario;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DestinatarioMapper {
	
	private ModelMapper modelMapper;
	
	public DestinatarioDTOOutput toDTO(Destinatario destinatario) {
		return modelMapper.map(destinatario, DestinatarioDTOOutput.class);
	}

}
