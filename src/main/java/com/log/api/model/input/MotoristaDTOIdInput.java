package com.log.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotoristaDTOIdInput {

	@NotNull
	private PersonagemDTOIdInput motorista;
}
