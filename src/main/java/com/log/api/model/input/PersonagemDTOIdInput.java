package com.log.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonagemDTOIdInput {

	@NotNull
	private Long id;
}
