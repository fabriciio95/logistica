package com.log.api.model.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class DestinatarioDTOOutput {
	
	private Long id;
	private String destinatario;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	private String recebedor;
	private String rgRecebedor;
	private EntregaIdDTOOutput entrega;
}
