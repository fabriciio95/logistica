package com.log.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.log.api.model.input.MotoristaDTOInput;
import com.log.api.model.output.EntregaDTOOutput;
import com.log.api.model.output.MotoristaDTOOutput;
import com.log.api.model.output.MotoristaEntregaDTOOutput;
import com.log.api.model.output.PersonagemResumoDTOOutput;
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
	
	public MotoristaEntregaDTOOutput toMotoristaEntregaDTO(Page<EntregaDTOOutput> entregas, Motorista motorista) {
		PersonagemResumoDTOOutput motoristaResumoDTO = modelMapper.map(motorista, PersonagemResumoDTOOutput.class);
		entregas.forEach(entrega -> entrega.setCliente(null));
		MotoristaEntregaDTOOutput motoristaEntregaDTO = new MotoristaEntregaDTOOutput();
		motoristaEntregaDTO.setMotorista(motoristaResumoDTO);
		motoristaEntregaDTO.setEntregas(entregas);
		return motoristaEntregaDTO;
	}
}
