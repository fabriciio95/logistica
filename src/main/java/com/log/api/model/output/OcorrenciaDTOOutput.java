package com.log.api.model.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaDTOOutput {

	private Long id;
	private String descricao;
	private OffsetDateTime dataRegistro;
}
