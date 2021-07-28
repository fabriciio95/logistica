package com.log.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;

import com.log.domain.ValidationGroups.atualizacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioDTOInput {
	
	@NotNull(groups = { atualizacao.class })
	@Null
	private Long id;

	@NotBlank(groups = { Default.class, atualizacao.class })
	private String destinatario;
	
	@NotBlank(groups = { Default.class, atualizacao.class })
	private String cep;
	
	@NotBlank(groups = { Default.class, atualizacao.class })
	private String rua;
	
	@NotBlank(groups = { Default.class, atualizacao.class })
	private String numero;
	
	@NotBlank(groups = { Default.class, atualizacao.class })
	private String bairro;
	
	@NotBlank(groups = { Default.class, atualizacao.class })
	private String cidade;
	
	@NotBlank(groups = { Default.class, atualizacao.class })
	private String estado;
	
	private String complemento;
	
	@NotBlank(groups = { atualizacao.class })
	private String recebedor;
	
	@NotBlank(groups = { atualizacao.class })
	private String rgRecebedor;
	
	@NotNull(groups = { atualizacao.class })
	@Valid
	private EntregaIdDTOInput entrega;
}
