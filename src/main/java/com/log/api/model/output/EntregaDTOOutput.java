package com.log.api.model.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.log.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class EntregaDTOOutput {

	private Long id;
	private ClienteResumoDTOOutput cliente;
	private DestinatarioDTOOutput destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	private List<ObjetoDTOOutput> objetos;
	private List<OcorrenciaDTOOutput> ocorrencias;
	
}
