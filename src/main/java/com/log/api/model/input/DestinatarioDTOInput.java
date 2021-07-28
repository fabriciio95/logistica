package com.log.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioDTOInput {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String rua;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	private String complemento;
	
	@NotBlank
	private String bairro;
	
}
