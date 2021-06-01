package com.log.api.model.output;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ClienteEntregaDTOOutput {

	private PersonagemResumoDTOOutput cliente;
	private Page<EntregaDTOOutput> entregas;
}
