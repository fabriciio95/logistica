package com.log.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjetoDTOInput {

	@NotBlank
	private String nome;
	
	@NotNull
	private Double peso;
}
