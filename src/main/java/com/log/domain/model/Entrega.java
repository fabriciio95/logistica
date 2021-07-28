package com.log.domain.model;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	
	private BigDecimal taxa;
	
	@OneToOne(mappedBy = "entrega", cascade = CascadeType.ALL)
	private Destinatario destinatario;
	
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
	
	
	public void colocarStatusEmAndamento() {
		if(!this.getStatus().equals(StatusEntrega.PENDENTE)) {
			throw new NegocioException("Entrega não esta com status pendente");
		}
		
		this.setStatus(StatusEntrega.EM_ANDAMENTO);
	}
	
	
	public void finalizar() {
		if(this.getStatus().equals(StatusEntrega.EM_ANDAMENTO)) {
			this.setStatus(StatusEntrega.FINALIZADA);
			this.setDataFinalizacao(OffsetDateTime.now());
		} else {
			throw new NegocioException("Entrega não pode ser finalizada, pois não está com status em andamento");
		}
	}
	
	public void cancelar() {
		if(this.getStatus().equals(StatusEntrega.PENDENTE) || this.getStatus().equals(StatusEntrega.EM_ANDAMENTO)) {
			this.setStatus(StatusEntrega.CANCELADA);
			this.setDataFinalizacao(OffsetDateTime.now());
		} else {
			String msg = status.equals(StatusEntrega.FINALIZADA) ? "finalizada" : "cancelada";
			throw new NegocioException(String.format("Entrega não pode ser cancelada, pois seu status atual ja é de %s", msg));
		}
	}
}
