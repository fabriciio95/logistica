package com.log.api.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonagemDTOOutput {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
}
