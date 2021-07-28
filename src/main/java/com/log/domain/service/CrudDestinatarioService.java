package com.log.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.log.domain.model.Destinatario;
import com.log.domain.repository.DestinatarioRepository;

@Service
public class CrudDestinatarioService {

	@Autowired
	private DestinatarioRepository destinatarioRepository;
	
	public Destinatario buscarDestinatarioEntrega(Long idEntrega) {
		return destinatarioRepository.findByEntregaId(idEntrega);
	}
}
