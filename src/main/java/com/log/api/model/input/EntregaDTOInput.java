package com.log.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaDTOInput {

	@Valid
	@NotNull
	private ClienteDTOIdInput cliente;
	
	@Valid
	@NotNull
	private DestinatarioDTOInput destinatario;
	
	@NotNull
	private BigDecimal taxa;	
}
