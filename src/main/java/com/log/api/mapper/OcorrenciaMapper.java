package com.log.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.log.api.model.output.OcorrenciaDTOOutput;
import com.log.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

	private ModelMapper modelMapper;
	
	public OcorrenciaDTOOutput toDTO(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTOOutput.class);
	}
	
	public List<OcorrenciaDTOOutput> toListDTO(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public Page<OcorrenciaDTOOutput> toPageDTO(Page<Ocorrencia> ocorrencias) {
		return ocorrencias.map(this::toDTO);
	}
}
