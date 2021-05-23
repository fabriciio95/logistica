package com.log.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaDTOInput {

	@NotBlank
	private String descricao;
}
