package com.log.domain.model;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.log.domain.exception.NegocioException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Motorista motorista;
	
	@Embedded
	private Destinatario destinatario;
	
	private BigDecimal taxa;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	@OneToMany(mappedBy = "entrega")
	private List<Objeto> objetos = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	private OffsetDateTime dataPedido;
	
	private OffsetDateTime dataFinalizacao;
	
	public Ocorrencia adicionarOcorrencia(String descricao) {
		if(!(this.getStatus().equals(StatusEntrega.PENDENTE) || this.getStatus().equals(StatusEntrega.EM_ANDAMENTO))) {
			String msg = status.equals(StatusEntrega.FINALIZADA) ? "finalizada" : "cancelada";
			throw new NegocioException(String.format("Status da entrega já está como %s", msg));
		}
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setEntrega(this);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencias.add(ocorrencia);
		return ocorrencia;
	}
	
	public void finalizarOuCancelar(StatusEntrega status) {
		if((!this.getStatus().equals(StatusEntrega.PENDENTE)) && (!this.getStatus().equals(StatusEntrega.EM_ANDAMENTO))) {
			String msg = status.equals(StatusEntrega.FINALIZADA) ? "finalizada" : "cancelada";
			throw new NegocioException(String.format("Entrega não pode ser %s", msg));
		}
		this.setStatus(status);
		this.setDataFinalizacao(OffsetDateTime.now());
	}
	
	public void colocarStatusEmAndamento() {
		if(!this.getStatus().equals(StatusEntrega.PENDENTE)) {
			throw new NegocioException("Entrega não esta com status pendente");
		}
		
		this.setStatus(StatusEntrega.EM_ANDAMENTO);
	}
}
