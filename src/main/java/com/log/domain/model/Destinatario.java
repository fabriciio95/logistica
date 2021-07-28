package com.log.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Destinatario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String destinatário;
	
	private String cep;

	private String rua;
	
	private String numero;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private String complemento;
	
	private String recebedor;
	
	private String rgRecebedor;
	
	@OneToOne
	private Entrega entrega;
	
}
