package com.log.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
public class Motorista extends Personagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
}
