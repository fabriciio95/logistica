package com.log.api.model.input;

import javax.validation.constraints.NotNull;

import com.log.domain.ValidationGroups.atualizacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaIdDTOInput {
	
	@NotNull(groups = { atualizacao.class })
	private Long id;

}
